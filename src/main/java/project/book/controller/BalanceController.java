package project.book.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.book.model.AccountTransactions;
import project.book.repository.AccountTranssactionsRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BalanceController {

    private final AccountTranssactionsRepository accountTranssactionsRepository;
    @GetMapping("/myBalance")
    public List<AccountTransactions> getBalance(@RequestParam("id") long id) {
        List<AccountTransactions> accountTransactions = accountTranssactionsRepository.findByCustomerIdOrderByTransactionDtDesc(id);

        if (accountTransactions != null) {
            return accountTransactions;
        } else {
            return null;
        }
    }
}
