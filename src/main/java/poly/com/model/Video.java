package poly.com.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor 
@AllArgsConstructor
@Builder
@Entity
@Table(name="Videos")
public class Video {

	@Id
	private String id;
	private String title;
	private String poster;
	private String description;
	private boolean active;
	private int views;
	@OneToMany(mappedBy = "video")
	private List<Favorite>favorites;
	private String link;
}
