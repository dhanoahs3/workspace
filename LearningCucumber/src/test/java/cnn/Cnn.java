package cnn;

import java.util.List;
import java.util.Map;

import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class Cnn {
	
	@Given("I open {string}")
    public void openBrowser(String browser){
		System.out.println("I open "+browser);
	}
	
	@And("go to {string}")
    public void navigate(String url){
		System.out.println("go to url "+url);
	}
	
	@Then("top menu should have the links {string}")
    public void topMenu(String links){
		System.out.println("top menu should have the links" +links);
	}
	
	/*@Then("top menu should have links")
    public void topMenuLinks(DataTable table){
		System.out.println("Top menu should have the links");
		System.out.println(table.asMaps());
		
		List<Map<String,String>> data = table.asMaps();
		System.out.println(data.get(0).get("Links"));
		System.out.println(data.get(0).get("Title"));
		System.out.println(data.get(1).get("Links"));
		System.out.println(data.get(1).get("Title"));
   }*/
	
	
	/*The below method is giving errors and needs to be checked
	 @Then("^title should contain keywords (.*)$")
	    public void titleKeywords(List<String> data){
			System.out.println("title should contain keywords "+data);
		}
	*/
	
    @Then("top menu should have links")
    public void topMenuLinks(List<TopLinks> topLinks){
    //	Assert.fail("Tests failed");
		System.out.println("Top menu should have the links");
		System.out.println(topLinks.get(0).link + "------------"+ topLinks.get(0).title);
		System.out.println(topLinks.get(1).link + "------------"+ topLinks.get(1).title);
	}
	
    @DataTableType
     public TopLinks entry(Map<String,String> entry){
    	System.out.println("X");
    	return new TopLinks(entry.get("Links"),entry.get("Title"));
    }
	
}
