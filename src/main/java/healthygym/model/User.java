package healthygym.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private String firstName;
    private String lastName;
    
    public User() {
    }
    
    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(firstName, user.firstName) &&
               Objects.equals(lastName, user.lastName) &&
               Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }
    
    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("User{id=").append(id).append(", firstName=")
                .append(firstName).append(", lastName=")
                .append(lastName).append("}");

        return builder.toString();
    }

}
