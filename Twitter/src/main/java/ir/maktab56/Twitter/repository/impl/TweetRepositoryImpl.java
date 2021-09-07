package ir.maktab56.Twitter.repository.impl;

import javax.persistence.*;
import ir.maktab56.Twitter.domain.Tweet;
import ir.maktab56.Twitter.repository.TweetRepository;
import ir.maktab56.Twitter.base.repository.impl.BaseEntityRepositoryImpl;

public class TweetRepositoryImpl extends BaseEntityRepositoryImpl<Tweet, Long> implements TweetRepository {

	public TweetRepositoryImpl(EntityManagerFactory emf) {
		super(emf);
	}
	
	@Override
	public Class<Tweet> getEntityClass() {
		return Tweet.class;
	}
	
	@Override
	public void delete(Tweet tweet) {
		//em.remove(tweet);
		em.remove(em.contains(tweet) ? tweet : em.merge(tweet));
	}
}
