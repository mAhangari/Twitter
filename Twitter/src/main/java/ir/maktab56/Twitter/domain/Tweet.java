package ir.maktab56.Twitter.domain;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Size;

import ir.maktab56.Twitter.base.domain.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "tweet_table")
public class Tweet extends BaseEntity<Long> {
	
	@Column(name = "description")
	@Size(min = 2, max = 280)
	private String description;
	
	@Column(name = "number_of_like", nullable = true)
	private Long numOfLike = 0L;
	
	@OneToMany(mappedBy = "tweets", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Comment> comments = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	public Tweet() {
	}
	
	public Tweet(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getNumOfLike() {
		return numOfLike;
	}

	public void setNumOfLike(Long numOfLike) {
		this.numOfLike = numOfLike;
	}
	
	public void like() {
		this.numOfLike += 1L;
	}
	
	public void disLike() {
		if(this.numOfLike > 0)
			this.numOfLike -= 1L;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public void addComment(Comment comment) {
		this.comments.add(comment);
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public String toString() {
		return "Description: '" + description + "' " +
				"number of like: '" + numOfLike + "' " +
				"comments: " + comments;
	}
}
