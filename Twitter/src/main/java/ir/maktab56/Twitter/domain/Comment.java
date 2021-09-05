package ir.maktab56.Twitter.domain;

import javax.persistence.*;

@Entity
@Table(name = "comment_table")
public class Comment {
	
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "tweet_id")
	private Tweet Tweets;
	
	public Comment() {
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
