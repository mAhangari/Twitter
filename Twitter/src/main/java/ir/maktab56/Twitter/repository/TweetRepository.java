package ir.maktab56.Twitter.repository;

import ir.maktab56.Twitter.domain.Tweet;
import ir.maktab56.Twitter.base.repository.BaseEntityRepository;

public interface TweetRepository extends BaseEntityRepository<Tweet, Long> {

	Class<Tweet> getEntityClass();

}
