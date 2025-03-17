package project.book.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.book.model.AccountTransactions;
import project.book.model.Customer;
import project.book.repository.AccountTranssactionsRepository;
import project.book.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BalanceController {

    private final AccountTranssactionsRepository accountTranssactionsRepository;
    private final CustomerRepository customerRepository;
    @GetMapping("/myBalance")
    public List<AccountTransactions> getBalance(@RequestParam("email") String email) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
        if (optionalCustomer.isPresent()) {
            List<AccountTransactions> accountTransactions =
                    accountTranssactionsRepository.findByCustomerIdOrderByTransactionDtDesc(optionalCustomer.get().getId());
            if (accountTransactions != null) {
                return accountTransactions;
            } else {
                return null;
            }
        }else {
            return null;
        }

    }
}
