package loantree.example.pidev.repository;

import loantree.example.pidev.Entities.Reclamation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReclamationRepository extends CrudRepository<Reclamation,Integer> {
}