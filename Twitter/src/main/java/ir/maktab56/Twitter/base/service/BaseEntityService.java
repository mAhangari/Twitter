package ir.maktab56.Twitter.base.service;

import java.util.Collection;
import java.util.List;
import ir.maktab56.Twitter.base.domain.BaseEntity;

public interface BaseEntityService<E extends BaseEntity<ID>, ID> {
	
	E save(E e);

    List<E> findAllById(Collection<ID> ids);

    List<E> findAll();

    void delete(E e);

    E findById(ID id);

    Boolean existsById(ID id);
    
}
