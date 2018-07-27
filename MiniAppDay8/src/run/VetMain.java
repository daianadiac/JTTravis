package run;

import entities.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class VetMain {

	public static void main(String[] args) {
		populateAndQuery();

	}

	public static void populateAndQuery(){

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("miniapp");
		EntityManager em = factory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();


		transaction.begin();
		//-------------------------------------------------------------------------------

		Animal a1 = new Animal();
		a1.setType("Dog");
		a1.setBreed("Corgi");
		em.persist(a1);

		Animal a2 = new Animal();
		a2.setType("Cat");
		a2.setBreed("British Shorthair");
		em.persist(a2);

		Animal a3 = new Animal();
		a3.setType("Rodent");
		a3.setBreed("Guinea Pig");
		em.persist(a3);

		Animal a4 = new Animal();
		a4.setType("Dog");
		a4.setBreed("Husky");
		em.persist(a4);

		Employee vet1 = new Vet();
		vet1.setName("Binu");
		((Vet) vet1).setSpeciality("Exotic");
		//vet1.setSpeciality("Exotic");
		em.persist(vet1);

		Employee ass1 = new Assistant();
		((Assistant) ass1).setRank("Min");
		ass1.setName("Mihai");
		em.persist(ass1);

		Employee ass2 = new Assistant();
		((Assistant) ass2).setRank("Veteran");
		ass2.setName("Damian");
		em.persist(ass2);

		Appointment ap1 = new Appointment();
		ap1.setAnimal(a3);
		ap1.setName("Harry");
		ap1.setVet((Vet) vet1);
		List<Assistant> team = new ArrayList<Assistant>();
		team.add((Assistant)ass1);
		team.add((Assistant)ass2);

		ap1.setTeam(team);

		em.persist(ap1);

		Appointment ap2 = new Appointment();
		ap2.setAnimal(a3);
		ap2.setName("Harry");
		ap2.setVet((Vet) vet1);
		List<Assistant> team2 = new ArrayList<Assistant>();
		team2.add((Assistant)ass2);
		team2.add((Assistant)ass1);

		ap2.setTeam(team2);

		em.persist(ap2);
		//-------------------------------------------------------------------------------

		transaction.commit();



		//-------------------------------------------------------------------------------
		//QUERY1
		transaction.begin();

		Query namedQuery1 = em.createNamedQuery("findAllBreedsOfType");
		namedQuery1.setParameter("type", "Dog");
		List<Animal> allBreeds = namedQuery1.getResultList();
		System.out.println("All Breeds of Type Dog:");
		for(Animal a: allBreeds){
			System.out.println( a.getBreed());
		}



		transaction.commit();



		//-------------------------------------------------------------------------------
		//QUERY2
		transaction.begin();

		Query namedQuery2 = em.createNamedQuery("findByID");
		Query interQuery = em.createNamedQuery("findCrew");
		interQuery.setParameter("id", 1);
		List<Appointment> list = interQuery.getResultList();
		System.out.println("Vetenerian for Appointment 1:");
		for(Appointment a : list){
			namedQuery2.setParameter("id", a.getVet().getId());
			List<Employee> emps = namedQuery2.getResultList();
			for (Employee e : emps){
				System.out.println(e.getName());
			}

		}
		transaction.commit();
		//-------------------------------------------------------------------------------
		//QUERY3
		transaction.begin();

		Query namedQuery3 = em.createNamedQuery("countAppointments");
		namedQuery3.setParameter("name", "Harry");
		long count = (long) namedQuery3.getSingleResult();
		System.out.println("No. of Appointments for Harry: " + count);

		transaction.commit();
		//-------------------------------------------------------------------------------
		//QUERY4
		transaction.begin();

		Query namedQuery4 = em.createNamedQuery("findCrew");
		namedQuery4.setParameter("id", 1);
		List<Appointment> apps = namedQuery4.getResultList();
		System.out.println("Team for appointment 1 consists of: ");
		for(Appointment a :apps){
			List<Assistant> ass = a.getTeam();
			for(Assistant as : ass){
				System.out.println(as.getName());
			}
		}
		transaction.commit();
		//-------------------------------------------------------------------------------


		//-------------------------------------------------------------------------------

		factory.close();


	}

}
