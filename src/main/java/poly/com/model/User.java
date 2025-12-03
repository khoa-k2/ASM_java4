package poly.com.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//lombok
@Data 
@NoArgsConstructor 
@AllArgsConstructor
@Builder
@Entity
@Table(name="Users")
public class User {


	@Id
	@Column(name="id",columnDefinition = "nvarchar(50)")
	private String id;
	
	@Column(name="password",columnDefinition = "nvarchar(50)")
	private String password;
	
	@Column(name="fullname",columnDefinition = "nvarchar(50)")
	private String fullname;
	
	@Column(name="email",columnDefinition = "nvarchar(50)")
	private String email;
	
	@Column(name="admin")
	private boolean admin= false;
	
	@OneToMany(mappedBy = "user")
	private List<Favorite>favorites;
	
	

	



	

}
