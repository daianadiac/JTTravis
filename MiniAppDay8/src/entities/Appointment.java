package entities;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQueries({
@NamedQuery(name = "findCrew",
			query = "SELECT ap FROM Appointment ap WHERE ap.id = :id"),
@NamedQuery(name = "countAppointments",
				query = "SELECT COUNT(a) FROM Appointment a WHERE a.name = :name")
		})
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;

	@OneToOne
	private Animal animal;

	@OneToOne
	private Vet vet;

	@OneToMany(targetEntity = Assistant.class)
	private List<Assistant> team;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Vet getVet() {
		return vet;
	}

	public void setVet(Vet vet) {
		this.vet = vet;
	}

	public List<Assistant> getTeam() {
		return team;
	}

	public void setTeam(List<Assistant> team) {
		this.team = team;
	}
}
