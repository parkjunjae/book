package project.book.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import project.book.model.Customer;
import project.book.repository.CustomerRepository;

import java.util.Date;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer){
        try {
            String hashPwd = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(hashPwd);
            customer.setCreateDt(new Date(System.currentTimeMillis()));
            Customer saveCustomer = customerRepository.save(customer);
                if (!customerRepository.existsByEmail(customer.getEmail())) {
                    if (saveCustomer.getId() > 0) {
                        return ResponseEntity.status(HttpStatus.CREATED)
                                .body("생성완료");
                    } else {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body("이메일을 잘못 입력했다");
                    }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("중복된 아이디 입니다.");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("생성 불가" + e.getMessage());

        }

    }
    @RequestMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication){
        Optional<Customer> customer = customerRepository.findByEmail(authentication.getName());
        return customer.orElse(null);

    }
}
