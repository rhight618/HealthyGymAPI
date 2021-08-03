package naggingann;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Medication {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	String name;
	String type;
	int dosageAmount;
	int dosageFrequency;
	String dosageDuration;	

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDosageAmount() {
		return dosageAmount;
	}
	public void setDosageAmount(int dosageAmount) {
		this.dosageAmount = dosageAmount;
	}
	public int getDosageFrequency() {
		return dosageFrequency;
	}
	public void setDosageFrequency(int dosageFrequency) {
		this.dosageFrequency = dosageFrequency;
	}
	public String getDosageDuration() {
		return dosageDuration;
	}
	public void setDosageDuration(String dosageDuration) {
		this.dosageDuration = dosageDuration;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	
}
