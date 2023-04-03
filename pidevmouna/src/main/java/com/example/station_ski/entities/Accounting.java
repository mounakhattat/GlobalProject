package com.example.station_ski.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "accounting")
public class Accounting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAccounting;

    @Column(name = "date_acc")
    private LocalDate dateAcc;

    @Column(name = "description_acc")
    private String descriptionAcc;

    @Column(name = "total_charges")
    private Double totalCharges;

    @Column(name = "total_revenues")
    private Double totalRevenues;

    @Column(name = "amount")
    private Double amount;

    public Accounting() {}

    public Accounting(LocalDate dateAcc, String descriptionAcc, Double totalCharges, Double totalRevenues , Double amount) {
        this.dateAcc = dateAcc;
        this.descriptionAcc = descriptionAcc;
        this.totalCharges = totalCharges;
        this.totalRevenues = totalRevenues;
        this.amount=amount;
    }


    public Accounting(double totalCharges, double totalRevenues) {
        this.totalCharges = totalCharges;
        this.totalRevenues = totalRevenues;
    }

    public Accounting(LocalDate now, String s, double totalcharges, double totalrevenues) {
        this.dateAcc = now;
        this.descriptionAcc = s;
        this.totalCharges = totalcharges;
        this.totalRevenues = totalrevenues;

    }

    public Long getIdAccounting() {
        return idAccounting;
    }

    public LocalDate getDateAcc() {
        return dateAcc;
    }

    public void setDateAcc(LocalDate dateAcc) {
        this.dateAcc = dateAcc;
    }

    public String getDescriptionAcc() {
        return descriptionAcc;
    }

    public void setDescriptionAcc(String descriptionAcc) {
        this.descriptionAcc = descriptionAcc;
    }

    public Double getTotalCharges() {
        return totalCharges;
    }

    public void setTotalCharges(Double totalCharges) {
        this.totalCharges = totalCharges;
    }

    public Double getTotalRevenues() {
        return totalRevenues;
    }

    public void setTotalRevenues(Double totalRevenues) {
        this.totalRevenues = totalRevenues;
    }



    public void setCharges(Set<Charge> charges) {
        this.charges = charges;
    }




    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @OneToMany(mappedBy="accounting", cascade=CascadeType.ALL)
    private List<Revenue> revenues;

    @OneToMany(mappedBy="accounting", cascade=CascadeType.ALL)
    private Set<Charge> charges;

    public Double getAmount() {
        return amount;
    }
    // constructeurs, getters et setters

    public double calculerChiffreAffaires() {
        double totalCharges = charges.stream().mapToDouble(Charge::getAmount).sum();
        double totalRevenues = revenues.stream().mapToDouble(Revenue::getAmount).sum();
        return totalRevenues - totalCharges;
    }

    public void setChiffreAffaires(Double chiffreAffaires) {
        double difference = chiffreAffaires - (totalRevenues - totalCharges);
        totalRevenues += difference;
    }

    private double chiffreAffaires;

    // Constructeur existant dans la classe Accounting
    // Getters et setters pour les autres attributs existants dans la classe Accounting

    public double getChiffreAffaires() {
        return chiffreAffaires;
    }

    public List<Revenue> getRevenues() {
        return revenues;
    }

    public void setRevenues(List<Revenue> revenues) {
        this.revenues = revenues;
    }



    public void addRevenue(Revenue revenue) {
        this.revenues.add(revenue);
    }

    public void addCharge(Charge charge) {
        this.charges.add(charge);
    }

    public void setChiffreAffaires(double chiffreAffaires) {
        this.chiffreAffaires = chiffreAffaires;
    }
    public void calculateTotalCharge() {
        double total = 0;
        for (Charge charge : charges) {
            total += charge.getSommeCharge();
        }
        this.totalCharges = total;
    }
}
