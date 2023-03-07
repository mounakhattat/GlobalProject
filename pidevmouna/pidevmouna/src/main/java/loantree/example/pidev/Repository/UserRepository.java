package loantree.example.pidev.Repository;

import loantree.example.pidev.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);

}
