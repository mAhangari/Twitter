package ir.maktab56.Twitter.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import ir.maktab56.Twitter.domain.Customer;
import ir.maktab56.Twitter.domain.Tweet;
import ir.maktab56.Twitter.repository.TweetRepository;
import ir.maktab56.Twitter.repository.impl.TweetRepositoryImpl;
import ir.maktab56.Twitter.service.BaseMenu;
import ir.maktab56.Twitter.service.TweetService;
import ir.maktab56.Twitter.util.ApplicationContext;
import ir.maktab56.Twitter.base.service.impl.BaseEntityServiceImpl;

public class TweetServiceImpl extends BaseEntityServiceImpl<Tweet, Long, TweetRepository> implements TweetService {
	
	private Scanner input = new Scanner(System.in);
	
	public TweetServiceImpl(TweetRepositoryImpl repository) {
		super(repository);
	}
	
	@Override
	public void delete(Tweet tweet) {
		EntityManager em = repository.getEntityManager();
		em.getTransaction().begin();
			repository.delete(tweet);
		em.getTransaction().commit();
		em.close();
	}

	public void createTweet(Customer customer) {

		BaseMenu.singleSetMessage("Insert Description");
		var description = input.nextLine();
		
		Tweet tweet = new Tweet(description);
		customer.addTweet(tweet);
		tweet.setCustomer(customer);
		ApplicationContext.tweetServ.save(tweet);
		BaseMenu.singlePrintMessage("Congratulations! Your Tweet Successfuly Submited!!!");
	}
	
	public void editTweet(Customer customer) {
		try {
			//customer.getTweets().stream().collect(Collectors.toList());
			BaseMenu.optionMessage(customer.getTweets()
					.stream()
					.map(a -> a.toString())
					.collect(Collectors.toList()), false);
			BaseMenu.singleSetMessage("Selet one of the above tweet to edit or enter '0' to exit");
			var tweetNum = input.nextInt();
			input.nextLine();
			if(tweetNum == 0)
				return;
			BaseMenu.singleSetMessage("Insert new description");
			var newDescription = input.nextLine();
			customer.getTweets().stream()
					.collect(Collectors.toList())
					.get(tweetNum - 1)
					.setDescription(newDescription);
			
		}catch(InputMismatchException e) {
			input.nextLine();
			BaseMenu.singlePrintMessage(BaseMenu.WRONG_NUMBER);
			editTweet(customer);
		}
	}
	
	public void deleteTweet(Customer customer) {
		try {
			List<Tweet> list = customer.getTweets()
								.stream().collect(Collectors.toList());
			BaseMenu.optionMessage(list.stream().map(a -> a.toString())
					.collect(Collectors.toList()), false);
			BaseMenu.singleSetMessage("Select one of the above tweet to delete or enter '0' to exit");
			var tweetNum = input.nextInt();
			input.nextLine();
			if(tweetNum == 0)
				return;
			
			ApplicationContext.tweetServ.delete(list.get(tweetNum - 1));
			customer.getTweets().remove(list.get(tweetNum - 1));
		}catch(InputMismatchException e) {
			input.nextLine();
			BaseMenu.singlePrintMessage(BaseMenu.WRONG_NUMBER);
			deleteTweet(customer);
		}
	}
	
	public void likeOrDislike(Customer customer) {
		try {
			List<Tweet> tweets = ApplicationContext.tweetServ.findAll();
			tweets = tweets.stream().collect(Collectors.toList());
			BaseMenu.optionMessage(tweets.stream().map(a -> a.toString())
					.collect(Collectors.toList()), false);
			BaseMenu.singleSetMessage("Select one of the above tweet to like or or dislike enter '0' to exit");
			var tweetNum = input.nextInt();
			input.nextLine();
			if(tweetNum == 0)
				return;
			BaseMenu.optionMessage(Arrays.asList("like", "disLike"), true);
			switch(input.nextInt()) {
			case 1:
				tweets.get(tweetNum - 1).like();
				customer.setTweets(tweets.stream().collect(Collectors.toSet()));
				ApplicationContext.tweetServ.save(tweets.get(tweetNum - 1));
				break;
				
			case 2:
				tweets.get(tweetNum - 1).disLike();
				customer.setTweets(tweets.stream().collect(Collectors.toSet()));
				ApplicationContext.tweetServ.save(tweets.get(tweetNum - 1));
				break;
			default:
				BaseMenu.singlePrintMessage(BaseMenu.WRONG_NUMBER);
				likeOrDislike(customer);
			}
		}catch(InputMismatchException e) {
			input.nextLine();
			BaseMenu.singlePrintMessage(BaseMenu.WRONG_NUMBER);
			likeOrDislike(customer);
		}
	}
	
}
