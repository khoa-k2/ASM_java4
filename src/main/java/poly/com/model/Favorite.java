package poly.com.model;

import java.util.Date;
import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//lombok
@Data @NoArgsConstructor 
@AllArgsConstructor
@Builder
@Entity
@Table(name="Favorites")
public class Favorite {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id ;
	@ManyToOne
	@JoinColumn(name="UserID")
private User user;
	@ManyToOne
	@JoinColumn(name="VideoID")
private Video video;
	@Temporal(TemporalType.DATE)
	@Column(name="LikeDate")
private Date likeDate;
	


}
