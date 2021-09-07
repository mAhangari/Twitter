package ir.maktab56.Twitter.repository.impl;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ir.maktab56.Twitter.domain.Customer;
import ir.maktab56.Twitter.domain.Profile;
import ir.maktab56.Twitter.repository.ProfileRepository;
import ir.maktab56.Twitter.base.repository.impl.BaseEntityRepositoryImpl;

public class ProfileRepositoryImpl extends BaseEntityRepositoryImpl<Profile, Long> 
					implements ProfileRepository {
	
	public static final String FETCH_GRAPH = "javax.persistence.fetchgraph";
	public static final String LOAD_GRAPH = "javax.persistence.loadgraph";
	
	public ProfileRepositoryImpl(EntityManagerFactory emf) {
		super(emf);
	}
	
	@Override
	public <T> Boolean existsByUsername(T username) {
		return em.createQuery(
				"SELECT COUNT(id) FROM Profile " +
				"WHERE username =: username AND isDeleted = 0",
				Long.class)
				.setParameter("username", username)
				.getSingleResult() == 1L;
	}

	@Override
	public <T> Profile findByUsername(T username) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Profile> cq = cb.createQuery(Profile.class);
		
		Root<Profile> root = cq.from(Profile.class);
		cq.where(cb.equal(root.get("username"), username));
		//cq.where(cb.equal(root.get("isDeleted"), 0));
		
		TypedQuery<Profile> typedQuery = em.createQuery(cq);
		EntityGraph<?> entityGraph = em.getEntityGraph(Customer.FETCH_TWEET_AND_COMMENT);
		typedQuery.setHint(
                LOAD_GRAPH, entityGraph
        );
		return typedQuery.getSingleResult();
//		return em.createQuery(
//				"SELECT c FROM Profile AS c "
//				+ "WHERE c.username =: Username AND isDeleted = 0 AND isActive = 1",
//				Profile.class)
//				.setParameter("Username", username)
//				.getSingleResult();
	}
	
	@Override
	public <UT, PT> Boolean existsByUsernameAndPassword(UT username, PT password) {
		return em.createQuery(
				"SELECT COUNT(id) FROM Profile " +
				"WHERE username =: username AND password =: password"
				+ " AND isDeleted = 0 AND isActive = 1"
				, Long.class)
				.setParameter("username", username)
				.setParameter("password", password)
				.getSingleResult() == 1L;
	}

	@Override
	public Class<Profile> getEntityClass() {
		return Profile.class;
	}
}
