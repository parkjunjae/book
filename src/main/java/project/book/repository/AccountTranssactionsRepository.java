package project.book.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.book.model.AccountTransactions;

import java.util.List;

@Repository
public interface AccountTranssactionsRepository extends CrudRepository<AccountTransactions, String> {

    List<AccountTransactions> findByCustomerIdOrderByTransactionDtDesc(long customerId);
}
