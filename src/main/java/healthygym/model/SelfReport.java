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
@Table(name = "selfreportings")
public class SelfReport {
	
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private Long userId;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp report_timestamp;
    
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
	public Timestamp getReport_timestamp() {
		return report_timestamp;
	}
	public void setReport_timestamp(Timestamp report_timestamp) {
		this.report_timestamp = report_timestamp;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SelfReport selfReport = (SelfReport) o;
        return Objects.equals(userId, selfReport.userId) &&
               Objects.equals(report_timestamp, selfReport.report_timestamp) &&
               Objects.equals(id, selfReport.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, report_timestamp);
    }
    
    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("SelfReport{id=").append(id).append(", userId=")
                .append(userId).append(", report_timestamp=")
                .append(report_timestamp).append("}");

        return builder.toString();
    }

}
