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
    private boolean fourteen_days;
    private boolean positive_test;
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
	public boolean isFourteen_days() {
		return fourteen_days;
	}
	public void setFourteen_days(boolean fourteen_days) {
		this.fourteen_days = fourteen_days;
	}
	public boolean isPositive_test() {
		return positive_test;
	}
	public void setPositive_test(boolean positive_test) {
		this.positive_test = positive_test;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SelfReport selfReport = (SelfReport) o;
        return Objects.equals(userId, selfReport.userId) &&
               Objects.equals(report_timestamp, selfReport.report_timestamp) &&
               Objects.equals(fourteen_days, selfReport.fourteen_days) &&
               Objects.equals(positive_test, selfReport.positive_test) &&
               Objects.equals(id, selfReport.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, fourteen_days, positive_test, report_timestamp);
    }
    
    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("SelfReport{id=").append(id).append(", userId=")
                .append(userId)
                .append(", fourteen_days=").append(fourteen_days)
                .append(", positive_test=").append(positive_test)
                .append(", report_timestamp=")
                .append(report_timestamp).append("}");

        return builder.toString();
    }

}
