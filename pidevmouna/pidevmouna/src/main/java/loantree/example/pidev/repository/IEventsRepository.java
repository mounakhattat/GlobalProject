package loantree.example.pidev.repository;
import loantree.example.pidev.Entities.Events;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IEventsRepository extends CrudRepository<Events,Integer>  {
}
