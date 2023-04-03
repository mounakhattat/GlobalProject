package com.example.station_ski.repositories;

import com.example.station_ski.entities.Accounting;
import com.example.station_ski.entities.Charge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountingRepository extends JpaRepository<Accounting, Integer> {
    Accounting findFirstByOrderByDateAccDesc();
}
