package com.example.station_ski.services;

import com.example.station_ski.entities.Revenue;

import java.time.LocalDate;
import java.util.List;

public interface IRevenueService {
    List<Revenue> retrieveAllRevenues();

    Revenue addRevenue(Revenue e);

    Revenue updateRevenue (Revenue e);

    Revenue retrieveRevenue (Integer revenueId);

    void deleteRevenue(Integer idRevenue);

    Revenue addRevenueToAccounting(Long idAccounting, Revenue revenue);
    List<Object[]> getMonthlyRevenues();
    double calculateAverageRevenue(LocalDate start, LocalDate end);
    List<Revenue> getRevenueByDate(LocalDate startDate, LocalDate endDate);
    Long calculateTotalRevenue(LocalDate startDate, LocalDate endDate);
}
