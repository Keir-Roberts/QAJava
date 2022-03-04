package Rest;

import java.util.List;
import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.repoPractise.Person;

import Service.PersonService;

@RestController
public class PersonController {
	
	private PersonService service;
	
	public PersonController(PersonService service) {
        super();
        this.service = service;
    }
	
	  @PostMapping("/create")
	    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
	        Person temp = this.service.addPerson(person);
	        return new ResponseEntity<Person>(temp, HttpStatus.CREATED); 
	    }

	    @GetMapping("/getAll")
	    public List<Person> getAllPeople() {
	        return this.service.getAllPeople();
	    }

	    @PutMapping("/update")
	    public Person updatePerson(@PathParam("id") long id, @RequestBody Person person) {
	        return this.service.updatePerson(id, person);
	    }

	    @DeleteMapping("/delete/{id}")
	    public Person removePerson(@PathVariable long id) {
	        Person temp = this.service.getPerson(id).orElse(null);
	    	this.service.removePerson(id);
	    	if(this.service.removePerson(id)) return temp;
	    	else return null;
	    }
}
