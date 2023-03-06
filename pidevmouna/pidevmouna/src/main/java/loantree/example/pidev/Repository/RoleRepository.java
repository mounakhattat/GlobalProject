package loantree.example.pidev.Repository;
import loantree.example.pidev.Entities.Role;
import loantree.example.pidev.Entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(Roles name);          //recuperer un role avec le nom
}
