package loantree.example.pidev.repository;

import loantree.example.pidev.Entities.Charge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ChargeRepository extends JpaRepository<Charge, Integer> {
   // List<Charge> findByDateBetween(LocalDate startDate, LocalDate endDate);

}
