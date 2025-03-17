package project.book.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.book.model.Customer;
import project.book.model.Loans;
import project.book.repository.CustomerRepository;
import project.book.repository.LoansRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class LoansController {

    private final LoansRepository loansRepository;
    private final CustomerRepository customerRepository;
    @GetMapping("/myLoans")
//    @PreAuthorize("hasRole('USER')")
    public List<Loans> getLoansDetails(@RequestParam("email") String email){
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
        if (optionalCustomer.isPresent()) {
            List<Loans> loans = loansRepository.findByCustomerIdOrderByStartDtDesc(optionalCustomer.get().getId());
            if (loans != null) {
                return loans;
            } else {
                return null;
            }
        }else {
            return null;
        }
    }
}
