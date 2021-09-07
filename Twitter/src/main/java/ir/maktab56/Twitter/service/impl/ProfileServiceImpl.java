package ir.maktab56.Twitter.service.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import ir.maktab56.Twitter.repository.impl.ProfileRepositoryImpl;
import ir.maktab56.Twitter.service.BaseMenu;
import ir.maktab56.Twitter.service.ProfileService;
import ir.maktab56.Twitter.util.ApplicationContext;
import ir.maktab56.Twitter.base.service.impl.BaseEntityServiceImpl;
import ir.maktab56.Twitter.domain.Admin;
import ir.maktab56.Twitter.domain.Customer;
import ir.maktab56.Twitter.domain.Profile;

public class ProfileServiceImpl extends BaseEntityServiceImpl<Profile, Long, ProfileRepositoryImpl> implements ProfileService {

	private Scanner input = new Scanner(System.in);
	
	public ProfileServiceImpl(ProfileRepositoryImpl repository) {
		super(repository);
	}
	
	@Override
	public Profile findByUsername(String username) {
		EntityManager em = repository.getEntityManager();
		try {
			return repository.findByUsername(username);
		}catch(NoResultException e) {
			return null;
		}finally {
			em.close();
		}
	}
	
	public void login() throws SQLException, ParseException {
		BaseMenu.singleSetMessage("Insert your Username");
		var username = input.next();
		
		BaseMenu.singleSetMessage("Insert your Password");
		var password = input.next();
		
		if(existsByUsernameAndPassword(username, password)) {
			BaseMenu.singlePrintMessage(BaseMenu.WELCOME);
			Profile profile = findByUsername(username);
			if(profile instanceof Customer)
				ApplicationContext.menu.loginCustomer((Customer) profile);
			else
				ApplicationContext.menu.loginAdmin((Admin) profile);
		}
		else {
			BaseMenu.singlePrintMessage("Username or Password Incorrect!!");
		}
	}
	
	@Override
	public Boolean existsByUsername(String username) {
		EntityManager em = repository.getEntityManager();
		try {
			return repository.existsByUsername(username);
		}catch(NoResultException e) {
			return false;
		}finally{
			em.close();
		}
	}
	
	@Override
	public Boolean existsByUsernameAndPassword(String username, String password) {
		EntityManager em = repository.getEntityManager();
		try {
			return repository.existsByUsernameAndPassword(username, password);
		}catch(NoResultException e) {
			return false;
		}finally{
			em.close();
		}
	}
	
	public void signUp() {
		var username = "";
		var password = "";
		
		BaseMenu.singleSetMessage("Insert First Name");
		var firstName = input.next();
		
		BaseMenu.singleSetMessage("Insert Last Name");
		var lastName = input.next();
		
		do{
			BaseMenu.singlePrintMessage("Username must have 3-15 charecter and must has one of these charecter: ([a-z0-9_-])");
			BaseMenu.singleSetMessage("Insert your Username");
			username = input.next();
			
		}while(ApplicationContext.chInInformation.checkUsername(username));
		
		do{
			BaseMenu.singlePrintMessage("Password must hase 8 or more charecter!!");
			BaseMenu.singleSetMessage("Insert your Password");
			password = input.next();
			
		}while(ApplicationContext.chInInformation.checkPassword(password));
		
		Customer customer = new Customer(username, password, firstName, lastName, true, null);
		ApplicationContext.profileServ.save(customer);
		BaseMenu.singlePrintMessage("Congratulations! Your Registration was Successful!!!");
	}
	
	public void changeEmailAddress(Profile profile) {
		try {
			var email = "";
			if(profile.getEmail().size() == 0) {
				BaseMenu.optionMessage(
						Arrays.asList("Add Email", "Exit"),
						true);
				switch(input.nextInt()) {
				case 1:
					do {
						BaseMenu.singleSetMessage("Insert email Address");
						email = input.next();
					}while(ApplicationContext.chInInformation.checkEmailAddress(email));
					profile.addEmail(email);
					break;
				case 2:
					break;
				default:
					BaseMenu.singlePrintMessage(BaseMenu.WRONG_NUMBER);
					changeEmailAddress(profile);
				}
			}
				
			else {
				BaseMenu.optionMessage(
						Arrays.asList("Add Email", "Edit email", "Exit"),
						true);
				switch(input.nextInt()) {
				case 1:
					do {
						BaseMenu.singleSetMessage("Insert email Address");
						email = input.next();
					}while(ApplicationContext.chInInformation.checkEmailAddress(email));
					
					profile.addEmail(email);
					break;
					
				case 2:
					List<String> emails = profile.getEmail()
					.stream()
					.collect(Collectors.toList());
					BaseMenu.optionMessage(emails, false);
					BaseMenu.singleSetMessage("Select an Email Address to Change or enter '0' to exit");
					
					var id = input.nextInt();
					if(id == 0)
						return;
			
					do {
						BaseMenu.singleSetMessage("Insert new email Address");
						email = input.next();
						emails.set(id - 1, email);
					}while(ApplicationContext.chInInformation.checkEmailAddress(email));
					profile.setEmail(emails.stream().collect(Collectors.toSet()));
					break;
					
				case 3:
					break;
				default:
					BaseMenu.singlePrintMessage(BaseMenu.WRONG_NUMBER);
					changeEmailAddress(profile);
				}
			}
		}catch(InputMismatchException e) {
			input.nextLine();
			BaseMenu.singlePrintMessage(BaseMenu.WRONG_NUMBER);
			changeEmailAddress(profile);
		}
	}

	public void changePhoneNumber(Profile profile) {
		try {
			var phoneNumber = "";
			if(profile.getPhoneNumber().size() == 0) {
				BaseMenu.optionMessage(
						Arrays.asList("Add phone number", "Exit"),
						true);
				switch(input.nextInt()) {
				case 1:
					do {
						BaseMenu.singleSetMessage("Insert phone number");
						phoneNumber = input.next();
					}while(ApplicationContext.chInInformation.checkPhoneNumber(phoneNumber));
					profile.addPhoneNumber(phoneNumber);
					break;
				case 2:
					break;
				default:
					BaseMenu.singlePrintMessage(BaseMenu.WRONG_NUMBER);
					changePhoneNumber(profile);
				}
			}
			
			else {
				BaseMenu.optionMessage(
						Arrays.asList("Add Phone number", "Edit phone number", "Exit"),
						true);
				switch(input.nextInt()) {
				case 1:
					do {
						BaseMenu.singleSetMessage("Insert phone number");
						phoneNumber = input.next();
					}while(ApplicationContext.chInInformation.checkPhoneNumber(phoneNumber));
					
					profile.addPhoneNumber(phoneNumber);
					break;
					
				case 2:
					List<String> phoneNumbers = profile.getPhoneNumber()
						.stream()
						.collect(Collectors.toList());
			
					BaseMenu.optionMessage(phoneNumbers, false);
					BaseMenu.singleSetMessage("Select an mobile number to Change or enter '0' to exit");
					
					var id = input.nextInt();
					if(id == 0)
						return;
			
					do {
						BaseMenu.singleSetMessage("Insert new mobile number Address");
						phoneNumber = input.next();
						phoneNumbers.set(id - 1, phoneNumber);
					}while(ApplicationContext.chInInformation.checkPhoneNumber(phoneNumber));
					
					profile.setEmail(phoneNumbers.stream().collect(Collectors.toSet()));
				case 3:
					break;
				default:
					BaseMenu.singlePrintMessage(BaseMenu.WRONG_NUMBER);
					changePhoneNumber(profile);
				}
			}
		}catch(InputMismatchException e) {
			input.nextLine();
			BaseMenu.singlePrintMessage(BaseMenu.WRONG_NUMBER);
			changePhoneNumber(profile);
		}
	}
	
	public <T extends Profile> void editProfile(T entity) {
		try {
			System.out.println(entity.hashCode());
			List<String> list = new ArrayList<>(
					Arrays.asList(
							"Change First name", "Change Last name",
							"Change Username", "Change Password",
							"Birthday", "Emails", "Mobile numbers",
							"Exit"));
			BaseMenu.optionMessage(list, true);
			
			switch(input.nextInt()) {
			case 1:
				BaseMenu.singleSetMessage("Enter new first name");
				var newFirstName = input.next();
				entity.setFirstName(newFirstName);
				editProfile(entity);
				break;
			case 2:
				BaseMenu.singleSetMessage("Enter new last name");
				var newLastName = input.next();
				entity.setLastName(newLastName);
				editProfile(entity);
				break;
			case 3:
				var newUsername = "";
				do{
					BaseMenu.singlePrintMessage("Username must have 3-15 charecter and must has one of these charecter: ([a-z0-9_-])");
					BaseMenu.singleSetMessage("Insert new Username");
					newUsername = input.next();
				}while(ApplicationContext.chInInformation.checkUsername(newUsername));
				
				entity.setUsername(newUsername);
				editProfile(entity);
				break;
			case 4:
				var newPassword = "";
				do{
					BaseMenu.singlePrintMessage("Password must hase 8 or more charecter!!");
					BaseMenu.singleSetMessage("Insert new Password");
					newPassword = input.next();
				}while(ApplicationContext.chInInformation.checkPassword(newPassword));
				entity.setPassword(newPassword);
				editProfile(entity);
				break;
			case 5:
				if(entity instanceof Customer) {
					Customer customer = null;
					var newBirthday = "";
					do {
						BaseMenu.singlePrintMessage("Date format: YYYY-MM-DD");
						
						customer = (Customer) entity;
						if(customer.getBirthday() == null)
							BaseMenu.singleSetMessage("Insert birthday");
						else
							BaseMenu.singleSetMessage("Insert new birthday");
						newBirthday = input.next();
					
						
					}while(ApplicationContext.chInInformation.checkDate(newBirthday));
					customer.setBirthday(Date.valueOf(newBirthday));
					editProfile(customer);
				}
				break;
			case 6:
				ApplicationContext.profileServ.changeEmailAddress(entity);
				editProfile(entity);
				break;
			case 7:
				ApplicationContext.profileServ.changePhoneNumber(entity);
				editProfile(entity);
				break;
			case 8:
				break;
			default:
				BaseMenu.singlePrintMessage(BaseMenu.WRONG_NUMBER);
				editProfile(entity);
			}
		}catch(InputMismatchException e) {
			input.nextLine();
			BaseMenu.singlePrintMessage(BaseMenu.WRONG_NUMBER);
			editProfile(entity);
		}
	}
	
}
