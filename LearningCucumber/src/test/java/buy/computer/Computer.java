package buy.computer;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class Computer {
	
	
	@Given("I want to buy  a PC")
    public void buy_pc(){
		System.out.println("I want to buy PC");
	}
	
	@And("^PC should be of (.*)$")
	public void pc_company(String company){
		System.out.println("PC should be of "+company);
	}
	
	@And("^It should be a (.*)$")
	public void pc_type(String type){
		System.out.println("It should be a "+type);
	}
	
	@And("^It should be a (.*) of size (\\d+)$")
	public void monitor_type(String monitor,int screenSize){
		System.out.println("It should be a "+monitor+ "of size "+screenSize);
	}
	
	@And("a {string} finish")
	public void finish(String type){
		System.out.println("a "+ type + " finish");
	}
	
	@And("I select a {int} TB hard drive")
	public void harddrive(int capacity){
		System.out.println("I select a " +capacity +" TB hard drive");
	}
	
	@And("^Ram should be (.*) Gb$")
	public void ram(int ramSize) {
		System.out.println("Ram should be "+ramSize);
	}
	
	
	@And("Price should be less than {int}")
	public void price(int price) {
		System.out.println("Price should be less than "+price);
	}
	
	@And("It should come with a bag")
	public void price() {
		System.out.println("It should come with a bag");
	}
	
	@And("^It should have (atleast|atmost|a maximum of) (\\d+) drives$")
	public void drives(String quantity,int number) {
		System.out.println("It should have "+quantity+" "+number+" drives");
	}
	
	
	

}
