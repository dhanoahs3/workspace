package select;

import car.Car;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Then;

public class SelectingCar {
	
	Car car;
	
	public SelectingCar(Car car){
		System.out.println("---------------------In constructor of selecting car------------------------");
		this.car = car;
	}
	
	@ParameterType("(.+?)")
	public User address(String addr){
		System.out.println(addr);
		User user = new User();
		String[] addressArray = addr.split(",");
		user.hNum = addressArray[0];
		user.street = addressArray[1];
		user.city = addressArray[2];
		user.country = addressArray[3];
		return user;
	}
	
	@And("I select city as {string}")
	public void select_city(String city){
		System.out.println("And I select city as "+city);
		System.out.println("The name of car in select city is --------->  "+car.carName);
	}
	
	@Then("I should get {string} cars in results")
	public void result(String color){
		System.out.println("I should get " + color +"  cars in result");
	}
	
   @And("Cars must be atleast {int} years old")
   public void city_old(int age){
	   System.out.println("Cars must be atleast "+ age +" years old");
   }
   
	@But("Cars should not be damaged")
	public void damaged(){
	System.out.println("Car should not be damaged");
	}
	
	@And("deliver this car to {address}")
	public void get_address(User user){
		System.out.println("deliver this car to "+user.printUser());
	}
	
	
	
	
	
}