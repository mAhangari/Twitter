package ir.maktab56.Twitter.service;

import java.util.*;
import java.util.stream.Collectors;
import java.sql.*;
import java.text.ParseException;
import ir.maktab56.Twitter.domain.Admin;
import ir.maktab56.Twitter.domain.Comment;
import ir.maktab56.Twitter.domain.Customer;
import ir.maktab56.Twitter.domain.Profile;
import ir.maktab56.Twitter.domain.Tweet;
import ir.maktab56.Twitter.service.dto.UserSearch;
import ir.maktab56.Twitter.util.ApplicationContext;

public class Menu extends BaseMenu {
	Scanner input = new Scanner(System.in);
	
	public void loginCustomer(Customer customer) throws SQLException, ParseException {
		try {
			showTweets();
			List<String> list = new ArrayList<>(
					Arrays.asList("Profile", "Tweets", "Comments",
							"Search Users", "Log Out"));
			optionMessage(list, true);
			
			switch(input.nextInt()) {
			case 1:
				showProfile(customer);
				loginCustomer(customer);
				break;
			case 2:
				tweets(customer);
				loginCustomer(customer);
				break;
			case 3:
				comments(customer);
				loginCustomer(customer);
				break;
			case 4:
				input.nextLine();
				searchUsers(customer);
				loginCustomer(customer);
				break;
			case 5:
				ApplicationContext.profileServ.save(customer);
				break;
			default:
				singlePrintMessage(WRONG_NUMBER);
				loginCustomer(customer);
			}
		}catch(InputMismatchException e) {
			input.nextLine();
			singlePrintMessage(WRONG_NUMBER);
			loginCustomer(customer);
		}
	}
	
	private void searchUsers(Customer customer) {
		singleSetMessage("Enter username");
		var customerUsername = input.nextLine();
		Profile entity = ApplicationContext.profileServ.findByUsername(customerUsername);
		Customer searchCustomer = null;
		if(entity != null && entity instanceof Customer) {
			searchCustomer = (Customer) entity;
			UserSearch userSerach = new UserSearch(searchCustomer.getFirstName(), searchCustomer.getLastName(), searchCustomer.getBirthday(), searchCustomer.getUsername(), searchCustomer.getEmail());
			singlePrintMessage(userSerach.toString());
		}
		else
			singlePrintMessage("this username dos not exist.");
	}

	private void showTweets() {
		List<Tweet> tweets = ApplicationContext.tweetServ.findAll();
		tweets.stream()
				.map(a -> a.toString())
				.forEach(BaseMenu::singlePrintMessage);
	}

	private void tweets(Customer customer) {
		try {
			List<String> list = new ArrayList<>(
					Arrays.asList("New Tweet",
								  "Show My Tweet",
								  "Like or dislike",
								  "Edit Tweet", "Delete Tweet", "Exit")
					);
			
			optionMessage(list, true);
			
			switch(input.nextInt()) {
			case 1:
				ApplicationContext.tweetServ.createTweet(customer);
				tweets(customer);
				break;
			case 2:
				if(customer.getTweets().size() != 0)
					customer.getTweets()
								.stream()
								.map(a -> a.toString())
								.forEach(BaseMenu::singlePrintMessage);
				tweets(customer);
				break;
			case 3:
				ApplicationContext.tweetServ.likeOrDislike(customer);
				tweets(customer);
				break;
			case 4:
				ApplicationContext.tweetServ.editTweet(customer);
				tweets(customer);
				break;
			case 5:
				ApplicationContext.tweetServ.deleteTweet(customer);
				tweets(customer);
				break;
			case 6:
				break;
			default:
				singlePrintMessage(WRONG_NUMBER);
				tweets(customer);
			}
			
		}catch(InputMismatchException e) {
			input.nextLine();
			singlePrintMessage(WRONG_NUMBER);
			tweets(customer);
		}
	}

	private void comments(Customer customer) {
		try {
			List<Tweet> list1 = customer.getTweets()
					.stream().collect(Collectors.toList());
			optionMessage(list1.stream().map(a -> a.toString())
							.collect(Collectors.toList()), false);
			
			List<String> list2 = new ArrayList<>(
					Arrays.asList("Add comment for tweet", "Edit comment",
							"Delete comment", "exit"));
			optionMessage(list2, true);
			
			switch(input.nextInt()) {
			case 1:
				singleSetMessage("Select a tweet to write comment");
				var tweetNum = input.nextInt();
				input.nextLine();
				singleSetMessage("Insert a comment");
				Comment comment = new Comment(input.nextLine());
				list1.get(tweetNum -1).addComment(comment);
				customer.setTweets(list1.stream().collect(Collectors.toSet()));
				
				comment.setTweets(list1.get(tweetNum -1));
				comment.setCustomer(customer);
				BaseMenu.singlePrintMessage("Congratulations! Your Comment Successfuly Submited!!!");
				comments(customer);
				break;
			case 2:
				ApplicationContext.commentServ.editComment(customer);
				break;
			case 3:
				ApplicationContext.commentServ.deleteComment(customer);
				comments(customer);
				break;
			case 4:
				break;
			default:
				singlePrintMessage(WRONG_NUMBER);
				comments(customer);
			}
		}catch(InputMismatchException e) {
			input.nextLine();
			singlePrintMessage(WRONG_NUMBER);
			comments(customer);
		}
	}
	
	public void showProfile(Customer customer) throws SQLException, ParseException {
		try {
			customer.toString();
			singlePrintMessage(customer.toString());
			List<String> list = new ArrayList<>(Arrays.asList("Edit Profile", "Delete Profile", "Exit"));
			optionMessage(list, true);
			
			switch(input.nextInt()) {
			case 1:
				ApplicationContext.profileServ.editProfile(customer);
				showProfile(customer);
				break;
			case 2:
				optionMessage(Arrays.asList("Delete this Account", "Exit"), true);
				try {
					switch(input.nextInt()) {
					case 1:
						ApplicationContext.profileServ.delete(customer);
						showMenu();
						break;
					case 2:
						showProfile(customer);
						break;
					default:
						singlePrintMessage(WRONG_NUMBER);
						showProfile(customer);
					}
				}catch(InputMismatchException e) {
					input.nextLine();
					singlePrintMessage(WRONG_NUMBER);
					showProfile(customer);
				}
			case 3:
				break;
			default:
				singlePrintMessage(WRONG_NUMBER);
				showProfile(customer);
			}
			
		}catch(InputMismatchException e) {
			input.nextLine();
			singlePrintMessage(WRONG_NUMBER);
			showProfile(customer);
		}
	}

	public void loginAdmin(Admin profile) {
		// TODO Auto-generated method stub
		
	}
	
	public void showMenu() throws SQLException, ParseException {
		try {
			List<String> list = new ArrayList<>(Arrays.asList("Login User", "Sign Up", "Exit"));
			optionMessage(list, true);
			
			switch(input.nextInt()) {
			case 1:
				ApplicationContext.profileServ.login();
				showMenu();
				break;
				
			case 2:
				ApplicationContext.profileServ.signUp();
				showMenu();
				break;
				
			case 3:
				break;
			default:
				singlePrintMessage(WRONG_NUMBER);
				showMenu();
			}
		}catch(InputMismatchException e) {
			input.nextLine();
			singlePrintMessage(WRONG_NUMBER);
			showMenu();
		}
		
	}

}
