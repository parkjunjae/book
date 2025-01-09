package project.book.Repository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.book.Entity.UserEntity;

@Repository
@AllArgsConstructor
@NoArgsConstructor
public class UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByUsername(String username);
    UserEntity findByUsername(String username);
}
