package entities;

import javax.persistence.*;

@Entity
@NamedQuery(name = "findAllBreedsOfType",
			query="SELECT a FROM Animal a WHERE a.type = :type")
public class Animal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String type;
	private String breed;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}
}
