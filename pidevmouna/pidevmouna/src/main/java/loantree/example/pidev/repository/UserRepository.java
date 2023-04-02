package loantree.example.pidev.repository;

import loantree.example.pidev.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
