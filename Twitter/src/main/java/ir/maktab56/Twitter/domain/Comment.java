package ir.maktab56.Twitter.domain;

import javax.persistence.*;

import ir.maktab56.Twitter.base.domain.BaseEntity;

@Entity
@Table(name = "comment_table")
public class Comment extends BaseEntity<Long> {
	
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "tweet_id")
	private Tweet tweets;
	
	public Comment() {
	}
	
	public Comment(String description) {
		this.setDescription(description);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Tweet getTweets() {
		return tweets;
	}

	public void setTweets(Tweet tweets) {
		this.tweets = tweets;
	}
	
	@Override
	public String toString() {
		return  "Username: '" + customer.getUsername() + "', " +
				"Description: '" + description + "' ";
	}
	
}
