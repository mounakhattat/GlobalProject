package loantree.example.pidev.repository;
import loantree.example.pidev.Entities.ERole;
import loantree.example.pidev.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(ERole name);          //recuperer un role avec le nom
}
