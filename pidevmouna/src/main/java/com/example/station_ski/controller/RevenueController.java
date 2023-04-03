package com.example.station_ski.controller;


import com.example.station_ski.entities.Revenue;
import com.example.station_ski.repositories.RevenueRepository;
import com.example.station_ski.services.RevenueService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/revenue")

public class RevenueController {
@Autowired
RevenueService revenueService;


    RevenueRepository revenueRepository;

    //http://localhost:8089/stationSki/revenue/retrieve-all-revenues
    @GetMapping("/retrieve-all-revenues")
    public List<Revenue> getRevenues() {
        return revenueService.retrieveAllRevenues();
    }
    // http://localhost:8089/stationSki/moniteur/retrieve-moniteur/8
    @GetMapping("/retrieve-revenues/{revenue-id}")
    public Revenue retrieveRevenues(@PathVariable("revenue-id") Integer revenueId) {
        return revenueService.retrieveRevenue(revenueId);
    }
    // http://localhost:8089/stationSki/moniteur/add-moniteur
    @PostMapping("/add-revenue")
    public Revenue addRevenue(@RequestBody Revenue e) {
        return revenueService.addRevenue(e);
    }
    // http://localhost:8089/stationSki/moniteur/remove-moniteur/1
    @DeleteMapping("/remove-revenue/{revenue-id}")
    public void removeRevenue(@PathVariable("revenue-id") Integer revenueId) {
        revenueService.deleteRevenue(revenueId);
    }
    // http://localhost:8089/stationSki/moniteur/update-moniteur
    @PutMapping("/update-revenue")
    public Revenue updateRevenue(@RequestBody Revenue e) {
        return revenueService.updateRevenue(e);
    }

    @GetMapping("/revenues/monthly")
    public String getMonthlyRevenues(Model model) {
        List<Object[]> monthlyRevenues = revenueService.getMonthlyRevenues();
        model.addAttribute("monthlyRevenues", monthlyRevenues);
        return "monthly-revenues";
    }
    @GetMapping("/average")
    public double getAverageRevenue(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return revenueService.calculateAverageRevenue(startDate, endDate);
    }
    @GetMapping("/revenueee")
    public List<Revenue> getRevenueByDate(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        return revenueService.getRevenueByDate(startDate, endDate);
    }

    @GetMapping("/total")
    public Long calculateTotalRevenue(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        return revenueService.calculateTotalRevenue(startDate, endDate);
    }

}