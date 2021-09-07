package ir.maktab56.Twitter.base.service.impl;

import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import ir.maktab56.Twitter.base.domain.BaseEntity;
import ir.maktab56.Twitter.base.repository.BaseEntityRepository;
import ir.maktab56.Twitter.base.service.BaseEntityService;

public abstract class BaseEntityServiceImpl<E extends BaseEntity<ID>, ID, R extends BaseEntityRepository<E, ID>> 
			implements BaseEntityService<E, ID> {
	
	protected final R repository;
	
	public BaseEntityServiceImpl(R repository) {
		this.repository = repository;
	}

	@Override
	public E save(E e) {
		EntityManager em = repository.getEntityManager();
		try {
			em.getTransaction().begin();
				e = repository.save(e);
			em.getTransaction().commit();
			return e;
		}catch(PersistenceException ex) {
			System.out.println("This entity can not save into database.");
			return e;
		}finally {
			em.close();
		}
	}

	@Override
	public List<E> findAllById(Collection<ID> ids) {
		try {
			return repository.findAllById(ids);
		}catch(NoResultException e) {
			return null;
		}
	}

	@Override
	public List<E> findAll() {
		EntityManager em = repository.getEntityManager();
		try{
			return repository.findAll();
		}catch(NoResultException e) {
			return null;
		}finally {
			em.close();
		}
	}

	@Override
	public void delete(E e) {
		EntityManager em = repository.getEntityManager();
		em.getTransaction().begin();
			repository.delete(e);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public E findById(ID id) {
		EntityManager em = repository.getEntityManager();
		try{
			return repository.findById(id);
		}catch(NoResultException e) {
			return null;
		}finally {
			em.close();
		}
	}

	@Override
	public Boolean existsById(ID id) {
		return repository.existsById(id);
	}

}
