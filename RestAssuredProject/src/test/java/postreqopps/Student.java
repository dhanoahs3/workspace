package postreqopps;

import java.util.ArrayList;
import java.util.List;

public class Student {
	

	public String firstName;
	public String lastName;
	public String email;
	public String programme;
	public List<String> courses;
	
	public Student(String firstName, String lastName, String email, String programme) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.programme = programme;
		this.courses = new ArrayList<String>();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProgramme() {
		return programme;
	}

	public void setProgramme(String programme) {
		this.programme = programme;
	}
	

	public List<String> getCourses() {
		return courses;
	}

	public void setCourses(String course) {
		courses.add(course);
	}

	
	
	
	
	


}
