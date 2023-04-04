package loantree.example.pidev.repository;

import loantree.example.pidev.Entities.Accounting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountingRepository extends JpaRepository<Accounting, Integer> {
    Accounting findFirstByOrderByDateAccDesc();
}
