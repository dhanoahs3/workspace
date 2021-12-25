package postreqex3;

import java.util.ArrayList;
import java.util.List;

public class Student {
	
	
	public String firstName;
	public String lastName;
	public String programme;
	public String email;
	public List<Course> courses;
	
	public Student(String firstName,  String lastName, String programme, String email)
	{
		this.firstName=firstName;
		this.lastName=lastName;
		this.programme=programme;
		this.email=email;
		courses = new ArrayList<Course>();
		
		
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getProgramme() {
		return programme;
	}
	public void setProgramme(String programme) {
		this.programme = programme;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public List<Course> getCourses() {
		return courses;
	}


	public void setCourse(List<Course> courses) {
		this.courses = courses;
	}
	
	
	public Course addCourse(String name, String score)
	{
		Course c = new Course(name, score); 
		
		courses.add(c);
		
		
		return c;
		
		
		
		
	}


	
	
	

}
