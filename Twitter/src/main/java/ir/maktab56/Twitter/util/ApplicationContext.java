package ir.maktab56.Twitter.util;

import ir.maktab56.Twitter.repository.impl.CommentRepositoryImpl;
import ir.maktab56.Twitter.repository.impl.ProfileRepositoryImpl;
import ir.maktab56.Twitter.repository.impl.TweetRepositoryImpl;
import ir.maktab56.Twitter.service.Menu;
import ir.maktab56.Twitter.service.impl.CommentServiceImpl;
import ir.maktab56.Twitter.service.impl.ProfileServiceImpl;
import ir.maktab56.Twitter.service.impl.TweetServiceImpl;

public class ApplicationContext {
	
	public static ProfileRepositoryImpl profileRepo = new ProfileRepositoryImpl(HibernateUtil.getEntityMangerFactory());
	public static ProfileServiceImpl profileServ = new ProfileServiceImpl(profileRepo);
	
	public static TweetRepositoryImpl tweetRepo = new TweetRepositoryImpl(HibernateUtil.getEntityMangerFactory());
	public static TweetServiceImpl tweetServ = new TweetServiceImpl(tweetRepo);
	
	public static CommentRepositoryImpl commentRepo = new CommentRepositoryImpl(HibernateUtil.getEntityMangerFactory());
	public static CommentServiceImpl commentServ = new CommentServiceImpl(commentRepo);

	public static Menu menu = new Menu();
	public static CheckInputInformation chInInformation = new CheckInputInformation();	
}

