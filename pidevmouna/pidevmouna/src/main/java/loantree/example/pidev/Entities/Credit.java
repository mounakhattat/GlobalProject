package loantree.example.pidev.Entities;


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
    private float interest_rate;
    @Enumerated(EnumType.STRING)
    private Status_Credit status_credit;
    private String adresse_mail;
    private Integer Duration;
    private Float Amount;


    @OneToMany(cascade = CascadeType.ALL, mappedBy="credit")
    private Set<Refund> refund;


    @OneToOne
    private User user;
}
