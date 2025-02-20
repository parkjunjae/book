package project.book.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.book.model.Accounts;
import project.book.repository.AccountsRepository;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountsRepository accountsRepository;

    @GetMapping("/myAccount")
    public Accounts getAccount(@RequestParam long id){
        Accounts accounts = accountsRepository.findByCustomerId(id);
        if(accounts != null) {
            return accounts;
        } else {
            return null;
        }
    }
}
