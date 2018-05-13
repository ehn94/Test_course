/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Program;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 *
 * @author Emilie
 */
public class CucumberTest {
    Triangle t1;
    Triangle triangle = new Triangle();
    String type;
   
    /*
    ^ starts with
    $ ends with
    \d digits
    */
    @Given("^The sides are (\\d+),(\\d+),(\\d+)$")
    public void the_sides_are(int arg1, int arg2, int arg3) throws Exception {
        this.t1 = new Triangle(arg1, arg2, arg3);
    }
    
    @When("^I ask what type of triangle$")
    public void i_ask_what_type_of_triangle() throws Exception {
        this.type = triangle.isTriangle(t1);
    }
    
    @Then("^I should get \"([^\"]*)\"$")
    public void i_should_get(String arg1) throws Exception {
        assertThat(type, is(arg1));
    }
    
}
