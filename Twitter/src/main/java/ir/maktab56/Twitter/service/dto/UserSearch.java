package ir.maktab56.Twitter.service.dto;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

public class UserSearch {

    private String firstName;

    private String lastName;
    
    private Date birthday;

    private Integer age;

    private String username;

    private Set<String> emails = new HashSet<>();
    
    public UserSearch(String firstName, String lastName, Date birthday, String username, Set<String> emails) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setBirthday(birthday);
		this.setUsername(username);
		this.setEmail(emails);
		this.setAge(age);
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
    
    public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getAge() {
    	LocalDate today = LocalDate.now();
		Period period = Period.between(birthday.toLocalDate(), today);
		setAge(period.getYears());
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<String> getEmail() {
        return emails;
    }

    public void setEmail(Set<String> emails) {
        this.emails = emails;
    }
    
    @Override
    public String toString() {
    	return "firstName= '" + firstName + "\' " +
               "lastName= '" + lastName + "\' " +
               "username= '" + username + "\' " +
               "age= '" + age + "\' " +
               "email='" + emails + "\' ";
    }
}
