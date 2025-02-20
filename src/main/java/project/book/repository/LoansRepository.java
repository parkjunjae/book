package project.book.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.book.model.Loans;

import java.util.List;

@Repository
public interface LoansRepository extends CrudRepository<Loans,Long> {

    List<Loans> findByCustomerIdOrderByStartDtDesc(long customerId);
}
