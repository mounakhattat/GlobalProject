package com.example.station_ski.services;

import com.example.station_ski.entities.Accounting;
import com.example.station_ski.entities.Revenue;

import com.example.station_ski.entities.RevenueSource;
import com.example.station_ski.repositories.AccountingRepository;
import com.example.station_ski.repositories.RevenueRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class RevenueService  implements IRevenueService{
    @Autowired

    private RevenueRepository revenueRepository;
    @Autowired
    private AccountingRepository accountingRepository;



    @Override
    public List<Revenue> retrieveAllRevenues() {

        return revenueRepository.findAll();
    }

    @Override
    public Revenue addRevenue(Revenue e) {
        return revenueRepository.save(e);
    }

    @Override
    public Revenue updateRevenue(Revenue e) {
        return revenueRepository.save(e);
    }

    @Override
    public Revenue retrieveRevenue(Integer idRevenue) {
        return revenueRepository.findById(idRevenue).get();
    }

    @Override
    public void deleteRevenue(Integer idRevenue) {
        revenueRepository.deleteById(idRevenue);
    }
    @Override
    public Revenue addRevenueToAccounting(Long idAccounting, Revenue revenue) {
        Accounting accounting = accountingRepository.findById(Math.toIntExact(idAccounting)).orElseThrow(() -> new RuntimeException("Accounting not found"));
        revenue.setAccounting(accounting);
        Revenue newRevenue = revenueRepository.save(revenue);
        return newRevenue;
    }
    public List<Object[]> getMonthlyRevenues() {
        return revenueRepository.getMonthlyRevenues();
    }

    @Override
    public double calculateAverageRevenue(LocalDate start, LocalDate end) {
        List<Revenue> revenues = revenueRepository.findAllByDateBetween(start, end);
        if (revenues.isEmpty()) {
            return 0.0;
        } else {
            double total = 0.0;
            for (Revenue revenue : revenues) {
                total += revenue.getAmount();
            }
            return total / revenues.size();
        }
    }
    @Override
    public List<Revenue> getRevenueByDate(LocalDate startDate, LocalDate endDate) {
        return revenueRepository.findByDateBetween(startDate, endDate);
    }





    public Long calculateTotalRevenue(LocalDate startDate, LocalDate endDate) {
        List<Revenue> revenues = revenueRepository.findByDateBetween(startDate, endDate);
        Long total = revenues.stream().mapToLong(Revenue::getAmount).sum();
        revenues.forEach(revenue -> revenue.setTotal(total));
        revenueRepository.saveAll(revenues);
        return total;
    }
}


