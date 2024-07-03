package com.clean_architecture.demo.archunit;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.clean_architecture.demo.print")
class ClassNamingTest {

    @ArchTest
    static final ArchRule interfacesShouldNotHaveNamesContainingTheWordInterface =
            noClasses()
                    .that().areInterfaces()
                    .should().haveSimpleNameContaining("Interface");

    @ArchTest
    static final ArchRule interfacesShouldNotHaveNamesStartingWithLetterI =
            noClasses()
                    .that().areInterfaces()
                    .should().haveNameMatching("I[A-Z].*");

    @ArchTest
    static ArchRule classesShouldNotContainImplPostfix =
            classes().that().areNotInterfaces()
                    .should().haveNameNotMatching(".*Impl")
                    .orShould().haveSimpleNameContaining("Builder") //Lombok generates Builders with Impl postfix
                    .orShould().haveSimpleNameContaining("Mapper"); //Mybatis generates classes with Impl postfix
}
