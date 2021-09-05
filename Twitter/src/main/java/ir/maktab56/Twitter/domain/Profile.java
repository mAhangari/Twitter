package ir.maktab56.Twitter.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import ir.maktab56.Twitter.base.domain.BaseEntity;

@Entity
@Table(name = Profile.TABLE_NAME)
@Inheritance(strategy = InheritanceType.JOINED)
public class Profile extends BaseEntity<Long> {

    public static final String TABLE_NAME = "profile_table";

    private String firstName;

    private String lastName;
    
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    private String password;
    
    @ElementCollection
    private Set<String> email;
    
    @ElementCollection
    private Set<String> mobileNumber = new HashSet<>();

    private Boolean isActive;

    public Profile() {
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id='" + getId() + '\'' +
                "firstName='" + firstName + '\'' +
                '}';
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

    public Set<String> getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Set<String> mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
