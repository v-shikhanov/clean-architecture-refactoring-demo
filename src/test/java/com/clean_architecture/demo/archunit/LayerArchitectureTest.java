package com.clean_architecture.demo.archunit;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "com.clean_architecture.demo.print")
class LayerArchitectureTest {

    private static final String DOMAIN = "Domain";
    private static final String APPLICATION = "Application";
    private static final String INFRASTRUCTURE = "Infrastructure";

    private static final DescribedPredicate<? super JavaClass> IS_PRINT =
            new DescribedPredicate<>("Is class belongs to print domain") {
                @Override
                public boolean test(JavaClass javaClass) {
                    return javaClass.getPackageName().startsWith("com.clean_architecture.demo.print");
                }
            };

    private static final DescribedPredicate<? super JavaClass> IS_ALLOWED =
            new DescribedPredicate<>("Is class belongs to language or allowed frameworks") {
                @Override
                public boolean test(JavaClass javaClass) {
                    var packageName = javaClass.getPackageName();
                    return packageName.startsWith("java")
                            || packageName.startsWith("org.springframework")
                            || packageName.startsWith("lombok")
                            || packageName.startsWith("org.slf4j")
                            || packageName.startsWith("jakarta.annotation")
                            || packageName.startsWith("org.apache");
                }
            };

    @ArchTest
    static final ArchRule layerDependenciesAreRespected = layeredArchitecture().consideringAllDependencies()
            // Layers declaration
            .layer(DOMAIN).definedBy("com.clean_architecture.demo.print.domain..")
            .layer(APPLICATION).definedBy("com.clean_architecture.demo.print.application..")
            .layer(INFRASTRUCTURE).definedBy("com.clean_architecture.demo.print.infrastructure..")

            // General rules about interaction between layers
            .whereLayer(INFRASTRUCTURE).mayNotBeAccessedByAnyLayer()
            .whereLayer(APPLICATION).mayOnlyBeAccessedByLayers(INFRASTRUCTURE)
            .whereLayer(DOMAIN).mayOnlyBeAccessedByLayers(APPLICATION, INFRASTRUCTURE)

            // Additional rules to make sure that other vertical slices are not used
            .whereLayer(APPLICATION).mayOnlyAccessLayers(DOMAIN)
            .whereLayer(DOMAIN).mayNotAccessAnyLayer()
            .ignoreDependency(IS_PRINT, IS_ALLOWED);
}
