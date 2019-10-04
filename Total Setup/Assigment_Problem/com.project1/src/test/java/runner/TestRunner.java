package runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)				
//@CucumberOptions(features="Features",glue={"StepDefinition"})	
 
@CucumberOptions(features = { "src/test/java/Features" }, glue = {
		
		"StepDefinition"}, plugin = { "pretty", "json:target/search.json",
				 }, monochrome = true)

public class TestRunner {

}
