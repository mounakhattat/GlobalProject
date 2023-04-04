package loantree.example.pidev.repository;

import loantree.example.pidev.Entities.Credit;

import loantree.example.pidev.Entities.Refund;
import loantree.example.pidev.Entities.Status_Credit;
import loantree.example.pidev.Entities.Type_Credit;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface CreditRepository extends JpaRepository<Credit, Integer> {





}


