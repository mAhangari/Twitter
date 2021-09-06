package ir.maktab56.Twitter.domain;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Customer extends Profile {

	@Column(name = "birthday")
	private Date birthday;
	
	@OneToMany(mappedBy = "customer")
	private Set<Comment> comments = new HashSet<>();
	
	@OneToMany(mappedBy = "customer")
	private Set<Comment> tweets = new HashSet<>();
	
	public Customer() {
		super();
	}
	
	public Customer(String username, String password, String firstName,
			String lastName, Boolean isActive, Date birthday) {
		super.setUsername(username);
		super.setPassword(password);
		super.setFirstName(firstName);
		super.setLastName(lastName);
		super.setActive(isActive);
		this.setBirthday(birthday);
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Set<Comment> getTweets() {
		return tweets;
	}

	public void setTweets(Set<Comment> tweets) {
		this.tweets = tweets;
	}

	public String toString() {
		return  "id='" + getId() + "\' " +
                "firstName= '" + getFirstName() + "\' " +
                "lastName= '" + getLastName() + "\' " +
                "username= '" + getUsername() + "\' " +
                "password= '" + getPassword() + "\' " +
                "birthday= '" + birthday + "\' " +
                //"email= " + getEmail() + " " +
                "phoneNumbers= " + getPhoneNumber();
	}
}
