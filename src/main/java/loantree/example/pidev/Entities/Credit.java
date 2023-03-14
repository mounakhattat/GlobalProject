package loantree.example.pidev.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.org.apache.xpath.internal.operations.Bool;
import loantree.example.pidev.Entities.Status_Credit;
import loantree.example.pidev.Entities.Type_Credit;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table( name = "Credit")
public class Credit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCredit")
    private Integer idCredit; // Clé primaire
    @Enumerated(EnumType.STRING)
    private Type_Credit type_credit;
    @Temporal(TemporalType.DATE)
    private Date start__date;
    @Temporal(TemporalType.DATE)
    private Date end__date;

    private float interest_rate;//taux d'intert
    @Enumerated(EnumType.STRING)
    private Status_Credit status_credit;
    private String adresse_mail;

    private Integer Duration ;
    private Float Amount ;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="credit")
    private Set<Refund> refund;

    public Credit() {

    }

    public Integer getIdCredit() {
        return idCredit;
    }

    public void setIdCredit(Integer idCredit) {
        this.idCredit = idCredit;
    }

    public Type_Credit getType_credit() {
        return type_credit;
    }

    public void setType_credit(Type_Credit type_credit) {
        this.type_credit = type_credit;
    }

    public Date getStart__date() {
        return start__date;
    }

    public void setStart__date(Date start__date) {
        this.start__date = start__date;
    }

    public Date getEnd__date() {
        return end__date;
    }

    public void setEnd__date(Date end__date) {
        this.end__date = end__date;
    }

    public float getInterest_rate() {
        return interest_rate;
    }

    public void setInterest_rate(float interest_rate) {
        this.interest_rate = interest_rate;
    }

    public Status_Credit getStatus_credit() {
        return status_credit;
    }

    public void setStatus_credit(Status_Credit status_credit) {
        this.status_credit = status_credit;
    }

    public String getAdresse_mail() {
        return adresse_mail;
    }

    public void setAdresse_mail(String adresse_mail) {
        this.adresse_mail = adresse_mail;
    }

    public Integer getDuration() {
        return Duration;
    }

    public void setDuration(Integer duration) {
        Duration = duration;
    }

    public Float getAmount() {
        return Amount;
    }

    public void setAmount(Float amount) {
        Amount = amount;
    }

    public Set<Refund> getRefund() {
        return refund;
    }

    public void setRefund(Set<Refund> refund) {
        this.refund = refund;
    }

    @Override
    public String toString() {
        return "Credit{" +
                "idCredit=" + idCredit +
                ", type_credit=" + type_credit +
                ", start__date=" + start__date +
                ", end__date=" + end__date +
                ", interest_rate=" + interest_rate +
                ", status_credit=" + status_credit +
                ", adresse_mail='" + adresse_mail + '\'' +
                ", Duration=" + Duration +
                ", Amount=" + Amount +
                ", refund=" + refund +
                '}';
    }

    public Credit(Integer idCredit, Type_Credit type_credit, Date start__date, Date end__date, float interest, float interest_rate, Status_Credit status_credit, String adresse_mail, Integer duration, Float amount, Set<Refund> refund) {
        this.idCredit = idCredit;
        this.type_credit = type_credit;
        this.start__date = start__date;
        this.end__date = end__date;
        this.interest_rate = interest_rate;
        this.status_credit = status_credit;
        this.adresse_mail = adresse_mail;
        Duration = duration;
        Amount = amount;
        this.refund = refund;
    }

}