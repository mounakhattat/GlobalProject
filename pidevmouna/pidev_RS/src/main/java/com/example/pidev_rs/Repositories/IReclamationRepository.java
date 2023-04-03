package com.example.pidev_rs.Repositories;
import com.example.pidev_rs.Entities.Reclamation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReclamationRepository extends CrudRepository<Reclamation,Integer> {
}