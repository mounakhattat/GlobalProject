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
    Credit credit;

}