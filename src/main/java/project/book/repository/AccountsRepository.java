package project.book.repository;

import lombok.extern.java.Log;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.book.model.AccountTransactions;
import project.book.model.Accounts;

@Repository
public interface AccountsRepository extends CrudRepository<Accounts, Long> {
    Accounts findByCustomerId(long customerId);
}
