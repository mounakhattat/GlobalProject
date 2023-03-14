package loantree.example.pidev.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table( name = "Refund")
public class Refund implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRefund")
    private Integer idRefund; // Clé primaire

    private float interest;
    private Double Monthly_Payment;

    private float Amortization;
    @Temporal(TemporalType.DATE)
    private Date Due_Date;
    @Temporal(TemporalType.DATE)
    private Date date_ref;
    private boolean status_ref;
    private float Remaining_Balence;
    private float penality;


    @ManyToOne
    loantree.example.pidev.Entities.Credit credit;

    public Refund() {

    }

    public Integer getIdRefund() {
        return idRefund;
    }

    public void setIdRefund(Integer idRefund) {
        this.idRefund = idRefund;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public Double getMonthly_Payment() {
        return Monthly_Payment;
    }

    public void setMonthly_Payment(Double monthly_Payment) {
        Monthly_Payment = monthly_Payment;
    }

    public float getAmortization() {
        return Amortization;
    }

    public void setAmortization(float amortization) {
        Amortization = amortization;
    }

    public Date getDue_Date() {
        return Due_Date;
    }

    public void setDue_Date(Date due_Date) {
        Due_Date = due_Date;
    }

    public Date getDate_ref() {
        return date_ref;
    }

    public void setDate_ref(Date date_ref) {
        this.date_ref = date_ref;
    }

    public boolean getStatus_ref() {
        return status_ref;
    }

    public void setStatus_ref(boolean status_ref) {
        this.status_ref = status_ref;
    }

    public float getRemaining_Balence() {
        return Remaining_Balence;
    }

    public void setRemaining_Balence(float remaining_Balence) {
        Remaining_Balence = remaining_Balence;
    }

    public float getPenality() {
        return penality;
    }

    public void setPenality(float penality) {
        this.penality = penality;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

//    @Override
//    public String toString() {
//        return "Refund{" +
//                "idRefund=" + idRefund +
//                ", interest=" + interest+
//                ", Monthly_Payment=" + Monthly_Payment +
//                ", Due_Date=" + Due_Date +
//                ", date_ref=" + date_ref +
//                ", status_ref=" + status_ref +
//                ", Remaining_Balence=" + Remaining_Balence +
//                ", penality=" + penality +
//                ", credit=" + credit +
//                '}';
//    }


    @Override
    public String toString() {
        return "Refund{" +
                "idRefund=" + idRefund +
                ", interest=" + interest +
                ", Monthly_Payment=" + Monthly_Payment +
                ", Amortization=" + Amortization +
                ", Due_Date=" + Due_Date +
                ", date_ref=" + date_ref +
                ", status_ref=" + status_ref +
                ", Remaining_Balence=" + Remaining_Balence +
                ", penality=" + penality +
                ", credit=" + credit +
                '}';
    }

//    public Refund(Integer idRefund, float interest, Double monthly_Payment, Date due_Date, Date date_ref, boolean status_ref, float remaining_Balence, float penality, Credit credit) {
//        this.idRefund = idRefund;
//        this.interest = interest;
//        Monthly_Payment = monthly_Payment;
//        Due_Date = due_Date;
//        this.date_ref = date_ref;
//        this.status_ref = status_ref;
//        Remaining_Balence = remaining_Balence;
//        this.penality = penality;
//        this.credit = credit;
//    }


    public Refund(Integer idRefund, float interest, Double monthly_Payment, float amortization, Date due_Date, Date date_ref, boolean status_ref, float remaining_Balence, float penality, loantree.example.pidev.Entities.Credit credit) {
        this.idRefund = idRefund;
        this.interest = interest;
        Monthly_Payment = monthly_Payment;
        Amortization = amortization;
        Due_Date = due_Date;
        this.date_ref = date_ref;
        this.status_ref = status_ref;
        Remaining_Balence = remaining_Balence;
        this.penality = penality;
        this.credit = credit;
    }
}