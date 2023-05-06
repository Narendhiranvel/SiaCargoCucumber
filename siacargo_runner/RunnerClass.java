package siacargo_runner;

import io.cucumber.testng.CucumberOptions;
import siacargo_stepdefinition.BaseClass;

@CucumberOptions(features = "src/test/java/siacargo_feature/Trackshipment.feature", 
glue = "siacargo_stepdefinition", monochrome = true, publish = true)

public class RunnerClass extends BaseClass {

}
