package ir.maktab56.Twitter.repository;

import ir.maktab56.Twitter.domain.Comment;
import ir.maktab56.Twitter.base.repository.BaseEntityRepository;

public interface CommentRepository extends BaseEntityRepository<Comment, Long> {

	Class<Comment> getEntityClass();

}