package ir.maktab56.Twitter.service;

import ir.maktab56.Twitter.domain.Profile;
import ir.maktab56.Twitter.base.service.BaseEntityService;

public interface ProfileService extends BaseEntityService<Profile, Long> {

	Profile findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByUsernameAndPassword(String username, String password);

}
