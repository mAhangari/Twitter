package ir.maktab56.Twitter.domain;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@NamedEntityGraph(name = Customer.FETCH_TWEET_AND_COMMENT,
			attributeNodes = {
					@NamedAttributeNode(value = "tweets", subgraph = "commentOfTweet"),
			},
			subgraphs = {
					@NamedSubgraph(name = "commentOfTweet",
							attributeNodes = {
									@NamedAttributeNode(value = "comments")
							}
						)
			}
			
		)
public class Customer extends Profile {
	
	public static final String FETCH_TWEET_AND_COMMENT = "FETCH_TWEET_AND_COMMENT";
	@Column(name = "birthday")
	private Date birthday;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Tweet> tweets = new HashSet<>();
	
	public Customer() {
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

	public Set<Tweet> getTweets() {
		return tweets;
	}

	public void setTweets(Set<Tweet> tweets) {
		this.tweets = tweets;
	}
	
	public void addTweet(Tweet tweet) {
		this.tweets.add(tweet);
	}
	
	@Override
	public String toString() {
		return  "id='" + getId() + "\' " +
                "firstName= '" + getFirstName() + "\' " +
                "lastName= '" + getLastName() + "\' " +
                "username= '" + getUsername() + "\' " +
                "password= '" + getPassword() + "\' " +
                "birthday= '" + birthday + "\' " +
                "email= " + getEmail() + " " +
                "phoneNumbers= " + getPhoneNumber();
	}
}
