package healthygym.model;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "checkins")
public class CheckIn {
	
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private Long userId;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp checkin_timestamp;
    private int risk_class;
    
    public CheckIn() {
    }
    
    public CheckIn(Long userId, Timestamp checkin_timestamp) {
        this.userId = userId;
        this.checkin_timestamp = checkin_timestamp;
    }
    
	public int getRisk_class() {
		return risk_class;
	}

	public void setRisk_class(int risk_class) {
		this.risk_class = risk_class;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
    public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Timestamp getCheckin_timestamp() {
		return checkin_timestamp;
	}

	public void setCheckin_timestamp(Timestamp checkin_timestamp) {
		this.checkin_timestamp = checkin_timestamp;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckIn checkIn = (CheckIn) o;
        return Objects.equals(userId, checkIn.userId) &&
               Objects.equals(checkin_timestamp, checkIn.checkin_timestamp) &&
               Objects.equals(id, checkIn.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, checkin_timestamp);
    }
    
    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("CheckIn{id=").append(id).append(", userId=")
                .append(userId).append(", checkin_timestamp=")
                .append(checkin_timestamp).append("}");

        return builder.toString();
    }

}
