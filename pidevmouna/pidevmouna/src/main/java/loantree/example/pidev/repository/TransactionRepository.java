package loantree.example.pidev.repository;

import java.util.List;

import loantree.example.pidev.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    @Query("SELECT t FROM Transaction t WHERE Type_transaction= :Typetransaction ")
    List<Transaction> findTransactionByTypetransaction(@Param("Typetransaction") String Typetransaction);
}
