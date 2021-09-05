package ir.maktab56.Twitter.base.domain;

import javax.persistence.*;

@MappedSuperclass
public class BaseEntity<ID> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private ID id;
	
	@Column(name = "is_Delet", columnDefinition = "TINYINT(1)")
    private Boolean isDeleted = false;
    
	public BaseEntity() {
	}
	
    public BaseEntity(Boolean isDeleted) {
		this.setId(id);
		this.setDeleted(isDeleted);
	}

	public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
