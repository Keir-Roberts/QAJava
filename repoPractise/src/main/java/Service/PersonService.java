package Service;

import java.util.List;
import java.util.Optional;

import com.qa.repoPractise.Person;

import repository.PersonRepo;

public class PersonService {

	private PersonRepo repo;

	public PersonService(PersonRepo repo) {
		super();
		this.repo = repo;
	}

	public Person addPerson(Person person) {
		return this.repo.save(person);
	}

	public List<Person> getAllPeople() {
		return this.repo.findAll();
	}

	public Optional<Person> getPerson(Long id) {
		return this.repo.findById(id);
	}
	
	public Person updatePerson(Long id, Person newPerson) {
		Optional<Person> existingOptional = this.repo.findById(id);
		Person existing = existingOptional.get();
		existing.setAge(newPerson.getAge());
		existing.setName(newPerson.getName());
		existing.setGender(newPerson.getGender());
		return this.repo.save(existing);
	}

	public boolean removePerson(Long id) {

		this.repo.deleteById(id);

		boolean exists = this.repo.existsById(id);

		return !exists;
	}
}
