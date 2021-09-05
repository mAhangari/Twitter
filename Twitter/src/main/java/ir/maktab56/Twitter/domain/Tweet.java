package ir.maktab56.Twitter.domain;

import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Size;
import javax.persistence.*;

@Entity
@Table(name = "tweet")
public class Tweet {
	
	@Column(name = "description")
	@Size(min = 3, max = 15)
	private String description;
	
	private boolean numOfLike;
	
	@OneToMany(mappedBy = "tweets")
	private Set<Comment> comments = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name = "tweets")
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

	public boolean isNumOfLike() {
		return numOfLike;
	}

	public void setNumOfLike(boolean numOfLike) {
		this.numOfLike = numOfLike;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
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
}
