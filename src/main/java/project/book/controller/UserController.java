package project.book.controller;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import project.book.constants.ApplicationConstants;
import project.book.model.Customer;
import project.book.model.LoginRequestDTO;
import project.book.model.LoginResponseDTO;
import project.book.repository.CustomerRepository;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final CustomerRepository customerRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final AuthenticationManager authenticationManager;
//    private final Environment env;


//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
//        try {
//            String hashPwd = passwordEncoder.encode(customer.getPwd());
//            customer.setPwd(hashPwd);
//            customer.setCreateDt(new Date(System.currentTimeMillis()));
//            Customer saveCustomer = customerRepository.save(customer);
//            if (customerRepository.existsByEmail(customer.getEmail())) {
//                if (saveCustomer.getId() > 0) {
//                    return ResponseEntity.status(HttpStatus.CREATED)
//                            .body("생성완료");
//                } else {
//                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                            .body("이메일을 잘못 입력했다");
//                }
//            } else {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("중복된 아이디 입니다.");
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("생성 불가" + e.getMessage());
//
//        }
//
//    }

    @RequestMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        Optional<Customer> customer = customerRepository.findByEmail(authentication.getName());
        return customer.orElse(null);

    }

//    @PostMapping("/apiLogin")
//    public ResponseEntity<LoginResponseDTO> apiLogin (@RequestBody LoginRequestDTO loginRequest) {
//        try {
//            log.info("LoginDTO : username={},password={}", loginRequest.username(),loginRequest.password());
//            String jwt = "";
//            Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.username(),
//                    loginRequest.password());
//            Authentication authenticationResponse = authenticationManager.authenticate(authentication);
//            if (null != authenticationResponse && authenticationResponse.isAuthenticated()) {
//                if (null != env) {
//                    String secret = env.getProperty(ApplicationConstants.JWT_SECRET_KEY,
//                            ApplicationConstants.JWT_SECRET_DEFAULT_VALUE);
//                    SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
//                    jwt = Jwts.builder().issuer("Eazy Bank").subject("JWT Token")
//                            .claim("username", authenticationResponse.getName())
//                            .claim("authorities", authenticationResponse.getAuthorities().stream().map(
//                                    GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
//                            .issuedAt(new java.util.Date())
//                            .expiration(new java.util.Date((new java.util.Date()).getTime() + 30000000))
//                            .signWith(secretKey).compact();
//                }
//            }
//            return ResponseEntity.status(HttpStatus.OK).header(ApplicationConstants.JWT_HEADER, jwt)
//                    .body(new LoginResponseDTO(HttpStatus.OK.getReasonPhrase(), jwt));
//        }catch (BadCredentialsException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponseDTO(HttpStatus.BAD_REQUEST.getReasonPhrase(),"잘못됨"));
//        }catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new LoginResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),"로그인 실패"));
//        }
//    }
}
