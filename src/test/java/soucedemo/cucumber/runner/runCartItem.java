package soucedemo.cucumber.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/soucedemo/cucumber/resources/features",
        glue = "soucedemo.cucumber.stepDef",
        plugin = {"html:target/HTML_report.html"},
        tags = "@Regresion"
)
public class runCartItem {
}
