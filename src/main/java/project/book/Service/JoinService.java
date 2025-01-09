package project.book.Service;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.book.Dto.JoinDto;
import project.book.Entity.UserEntity;
import project.book.Repository.UserRepository;

@Service
public class JoinService {
    private final UserRepository userRepository;
    private final BCyrptPasswordEncoder bCryptPasswordEncoder;

    public void Joinprocess(JoinDto joinDto){
        UserEntity userEntity = new UserEntity();
        userEntity.setName(joinDto.getName());
        userEntity.setPassword(bCryptPasswordEncoder(joinDto.getPassword()));
    }

}
