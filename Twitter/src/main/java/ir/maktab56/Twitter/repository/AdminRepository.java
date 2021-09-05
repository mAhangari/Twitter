package ir.maktab56.Twitter.repository;

import ir.maktab56.Twitter.domain.Admin;
import ir.maktab56.Twitter.base.repository.BaseEntityRepository;

public interface AdminRepository extends BaseEntityRepository<Admin, Long> {

	Class<Admin> getEntityClass();
	
	<T> boolean checkUsername(T username);

	<UT, PT> boolean checkAdminInfo(UT username, PT password);

	<T> Admin findByUsername(T username);

}
