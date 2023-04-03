package com.example.pidev_rs.Repositories;
import com.example.pidev_rs.Entities.Events;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IEventsRepository extends CrudRepository<Events,Integer>  {
}
