package ir.maktab56.Twitter.repository;

import ir.maktab56.Twitter.domain.Profile;
import ir.maktab56.Twitter.base.repository.BaseEntityRepository;

public interface ProfileRepository extends BaseEntityRepository<Profile, Long> {

	Class<Profile> getEntityClass();
		
	<T> Profile findByUsername(T username);

	<UT, PT> Boolean existsByUsernameAndPassword(UT username, PT password);

	<T> Boolean existsByUsername(T username);

}
