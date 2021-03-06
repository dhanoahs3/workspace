package search;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import car.Car;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import io.cucumber.java.Scenario;

public class SearchingCar {
	
	WebDriver driver;
	
	Car car;
	
	public SearchingCar(Car car){
		System.out.println("---------------------In constructor of searching car------------------------");
		this.car = car;
	}
	
	@Before
	public void init(Scenario s){
		System.out.println("Before----------------->"+s.getName());
	}
	
	
	@After
	public void quit(Scenario s){
		System.out.println("After----------------->"+s.getName());
		driver.quit();
	}
	
	
	@ParameterType("(.+?)")
	public String colors(String allColors){
		System.out.println(allColors);
		return allColors.split(",")[2];
	}
	


	@Given("I go to buy car")
      public void buy_car(){
		  System.out.println("I go to buy car");
		  car.carName = "Audi";

	  System.setProperty("webdriver.chrome.driver","C:\\drivers\\latest\\chromedriver.exe");
      driver = new ChromeDriver();
      System.out.println("The driver in buy_car is ----> "+driver);
}
	
	@And("Car must of {string}")
	public void car_make(String make){
		System.out.println("Car must of "+make);
		System.out.println("The name of car is in searching car is ------------> "+car.carName);

      //  	Sharing chrome driver instance between various methods	
		System.out.println("The driver in make_car is ----> "+driver);
	      driver.get("https://www.google.com");
     }
	
	@And("Car can be {colors} in color")
	public void car_color(String color){
		System.out.println("Car can be "+color +"  in color");
	}
	
	@When("I search for car")
	public void search_car(){
		System.out.println("I search for car");
	}
	
	
	
	
	
	
	 
	
	  
	
}

