package ir.maktab56.Twitter.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin extends Profile {
	
	@Column(name = "is_super_admin", columnDefinition = "TINYINT(1)")
	private Boolean isSuperAdmin;
	
	public Admin() {
		super();
	}
	
	public Admin(String username, String password, String firstName,
			String lastName, Boolean isActive, Boolean isSuperAdmin) {
		super.setUsername(username);
		super.setPassword(password);
		super.setFirstName(firstName);
		super.setLastName(lastName);
		super.setActive(isActive);
		this.setIsSuperAdmin(isSuperAdmin);
	}

	public Boolean getIsSuperAdmin() {
		return isSuperAdmin;
	}

	public void setIsSuperAdmin(Boolean isSuperAdmin) {
		this.isSuperAdmin = isSuperAdmin;
	}
	
	public String toString() {
		return "Id: " + getId() + ", First Name: " + getFirstName() + ", Last Name: " + getLastName()
				+ "\nUserName: " + getUsername();
	}
}
