package entities;

import org.eclipse.persistence.annotations.PrimaryKey;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "ID")
public class Vet extends Employee {

	private String speciality;

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
}
