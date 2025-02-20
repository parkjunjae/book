package project.book.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.book.model.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact,String> {

}
