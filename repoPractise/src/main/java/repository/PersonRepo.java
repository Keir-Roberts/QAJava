package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.repoPractise.Person;

@Repository
public interface PersonRepo extends JpaRepository<Person, Long>{

}
