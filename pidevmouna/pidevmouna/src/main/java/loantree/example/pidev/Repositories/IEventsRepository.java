package loantree.example.pidev.Repositories;
import loantree.example.pidev.Entities.Events;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IEventsRepository extends CrudRepository<Events,Integer>  {
}
