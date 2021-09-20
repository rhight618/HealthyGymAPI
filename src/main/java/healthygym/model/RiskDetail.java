package healthygym.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "riskdetail")
public class RiskDetail {
	
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private int hour_value;
    private int day_of_week;
    private int risk_class;
    
    public RiskDetail() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getHour() {
		return hour_value;
	}

	public void setHour(int hour_value) {
		this.hour_value = hour_value;
	}

	public int getDay_of_week() {
		return day_of_week;
	}

	public void setDay_of_week(int day_of_week) {
		this.day_of_week = day_of_week;
	}

	public int getRisk_class() {
		return risk_class;
	}

	public void setRisk_class(int risk_class) {
		this.risk_class = risk_class;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RiskDetail risksdetail = (RiskDetail) o;
        return Objects.equals(hour_value, risksdetail.hour_value) &&
               Objects.equals(day_of_week, risksdetail.day_of_week) &&
               Objects.equals(id, risksdetail.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hour_value, day_of_week);
    }
    
    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("RiskDetail{id=").append(id).append(", hour=")
                .append(hour_value).append(", day_of_week=")
                .append(day_of_week).append("}");

        return builder.toString();
    }

}
