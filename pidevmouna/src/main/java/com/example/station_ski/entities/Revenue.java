package com.example.station_ski.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "revenue")
public class Revenue implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRevenue")
    private Integer idRevenue;

    private Long amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "source")
    private RevenueSource source;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accounting_id")
    private Accounting accounting;

    @Column(name = "date")
    private LocalDate date;
    private Long total;

    public Revenue() {}

    public Revenue(Long amount, RevenueSource source, Accounting accounting, LocalDate date) {
        this.amount = amount;
        this.source = source;
        this.accounting = accounting;
        this.date = date;
    }

    public Integer getIdRevenue() {
        return idRevenue;
    }

    public void setIdRevenue(Integer idRevenue) {
        this.idRevenue = idRevenue;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public RevenueSource getSource() {
        return source;
    }

    public void setSource(RevenueSource source) {
        this.source = source;
    }

    public Accounting getAccounting() {
        return accounting;
    }

    public void setAccounting(Accounting accounting) {
        this.accounting = accounting;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
