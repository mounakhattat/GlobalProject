package com.example.station_ski.repositories;

import com.example.station_ski.entities.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RevenueRepository extends JpaRepository<Revenue, Integer> {

    @Query("SELECT YEAR(r.date), MONTH(r.date), SUM(r.amount) FROM Revenue r GROUP BY YEAR(r.date), MONTH(r.date)")
    List<Object[]> getMonthlyRevenues();
    List<Revenue> findAllByDateBetween(LocalDate start, LocalDate end);
    List<Revenue> findByDateBetween(LocalDate startDate, LocalDate endDate)
            ;


}
