package entities;

import javax.persistence.Entity;

@Entity
public class Assistant extends Employee{

	private String rank;

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}
}
