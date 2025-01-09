package project.book.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "loginDate")
    private LocalDateTime loginDate;

    @PrePersist
    public void createDt(){
        this.loginDate = LocalDateTime.now();
    }

}
