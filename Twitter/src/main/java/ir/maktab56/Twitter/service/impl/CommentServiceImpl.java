package ir.maktab56.Twitter.service.impl;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

//import java.util.*;
import javax.persistence.EntityManager;
import ir.maktab56.Twitter.domain.Comment;
import ir.maktab56.Twitter.domain.Customer;
import ir.maktab56.Twitter.repository.CommentRepository;
import ir.maktab56.Twitter.repository.impl.CommentRepositoryImpl;
import ir.maktab56.Twitter.service.BaseMenu;
import ir.maktab56.Twitter.service.CommentService;
import ir.maktab56.Twitter.util.ApplicationContext;
import ir.maktab56.Twitter.base.service.impl.BaseEntityServiceImpl;

public class CommentServiceImpl extends BaseEntityServiceImpl<Comment, Long, CommentRepository> implements CommentService {
	
	private Scanner input = new Scanner(System.in);
	
	public CommentServiceImpl(CommentRepositoryImpl repository) {
		super(repository);
	}
	
	@Override
	public void delete(Comment comment) {
		EntityManager em = repository.getEntityManager();
		em.getTransaction().begin();
			repository.delete(comment);
		em.getTransaction().commit();
		em.close();
	}
	
	public void editComment(Customer customer) {
		try {
			List<Comment> list = customer.getTweets()
					.stream()
					.map(a -> a.getComments())
					.collect(Collectors.toList()).get(0);
			
			BaseMenu.optionMessage(list
					.stream().map(a -> a.toString())
					.collect(Collectors.toList())
					, false);
			BaseMenu.singleSetMessage("Selet one of the above comment to edit or enter '0' to exit");
			var commentNum = input.nextInt();
			input.nextLine();
			if(commentNum == 0)
				return;
			BaseMenu.singleSetMessage("Insert new description");
			var newDescription = input.nextLine();
			customer.getTweets().stream()
						.filter(a -> a.getComments()
									.get(commentNum -1) == a.getComments()
									.get(commentNum -1))
						.map(a -> a.getComments())
						.collect(Collectors.toList())
						.get(0)
						.get(0).setDescription(newDescription);
			
		}catch(InputMismatchException e) {
			input.nextLine();
			BaseMenu.singlePrintMessage(BaseMenu.WRONG_NUMBER);
			editComment(customer);
		}
	}
	
	public void deleteComment(Customer customer) {
		try {
			List<Comment> list = customer.getTweets()
					.stream()
					.map(a -> a.getComments())
					.collect(Collectors.toList()).get(0);
			
			BaseMenu.optionMessage(list
					.stream().map(a -> a.toString())
					.collect(Collectors.toList())
					, false);
			BaseMenu.singleSetMessage("Select one of the above comment to delete or enter '0' to exit");
			var commentNum = input.nextInt();
			input.nextLine();
			if(commentNum == 0)
				return;
			
			Comment comment = customer.getTweets().stream()
					.map(a -> a.getComments())
					.collect(Collectors.toList()).get(0).remove(commentNum - 1);
			
			ApplicationContext.commentServ.delete(comment);
			
		}catch(InputMismatchException e) {
			input.nextLine();
			BaseMenu.singlePrintMessage(BaseMenu.WRONG_NUMBER);
			deleteComment(customer);
		}
	}
	
}