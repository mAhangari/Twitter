package ir.maktab56.Twitter.repository.impl;

import javax.persistence.EntityManagerFactory;
import ir.maktab56.Twitter.base.repository.impl.BaseEntityRepositoryImpl;
import ir.maktab56.Twitter.domain.Comment;
import ir.maktab56.Twitter.repository.CommentRepository;

public class CommentRepositoryImpl extends BaseEntityRepositoryImpl<Comment, Long> implements CommentRepository {

	public CommentRepositoryImpl(EntityManagerFactory emf) {
		super(emf);
	}
	
	@Override
	public Class<Comment> getEntityClass() {
		return Comment.class;
	}
	
	@Override
	public void delete(Comment comment) {
		//em.remove(comment);
		em.remove(em.contains(comment) ? comment : em.merge(comment));
	}
}