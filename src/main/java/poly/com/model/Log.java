package poly.com.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "Logs")
public class Log {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int id;

	private String url;
	private Date time;
	private String username;

	public Log(String url, Date time, String username) {
		super();
		this.url = url;
		this.time = time;
		this.username = username;
	}

}
