package ir.maktab56.Twitter.repository;

import ir.maktab56.Twitter.domain.Customer;
import ir.maktab56.Twitter.base.repository.BaseEntityRepository;

public interface CustomerRepository extends BaseEntityRepository<Customer, Long> {

	Class<Customer> getEntityClass();
	
	<T> boolean checkUsername(T username);

	<UT, PT> boolean checkCustomerInfo(UT username, PT password);
	
	<T> Customer findByUsername(T username);

}
