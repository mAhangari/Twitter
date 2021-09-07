package ir.maktab56.Twitter.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import ir.maktab56.Twitter.base.domain.BaseEntity;

@Entity
@Table(name = Profile.TABLE_NAME)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@NamedEntityGraph(name = Profile.FETCH_EMAIL_AND_PHONENUMBER,
//		attributeNodes = {
//				@NamedAttributeNode(value = "email"),
//				@NamedAttributeNode(value = "phoneNumbers")
//		}
//	)

public class Profile extends BaseEntity<Long> {

    public static final String TABLE_NAME = "profile_table";
    public static final String FETCH_EMAIL_AND_PHONENUMBER = "FETCH_EMAIL_AND_PHONENUMBER";

    private String firstName;

    private String lastName;
    
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    private String password;
    
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "profile_email", joinColumns = @JoinColumn(name = "profile_id"))
    private Set<String> email;
    
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> phoneNumbers = new HashSet<>();

    private Boolean isActive;

    public Profile() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getEmail() {
        return email;
    }

    public void setEmail(Set<String> email) {
        this.email = email;
    }
    
    public void addEmail(String email) {
    	this.email.add(email);
    }

    public Set<String> getPhoneNumber() {
        return phoneNumbers;
    }

    public void setPhoneNumber(Set<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
    
    public void addPhoneNumber(String phoneNumber) {
    	this.phoneNumbers.add(phoneNumber);
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
    
    @Override
    public String toString() {
        return "Profile:\n" +
                "id='" + getId() + "\'\t" +
                "firstName='" + firstName + "\'\t" +
                "lastName='" + lastName + "\'\n" +
                "username='" + username + "\'\t" +
                "password='" + password + "\'\t" +
                "email='" + email + "\'\n" +
                "phoneNumbers='" + phoneNumbers + "\'\n" +
                '}';
    }
}
