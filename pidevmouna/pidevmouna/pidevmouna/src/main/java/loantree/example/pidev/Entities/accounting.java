package loantree.example.pidev.Entities;


import java.io.Serializable;

@Entity
@Table(name = "accounting")
public class accounting implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accounting_ID")
    private Long id; // Identifiant (Clé primaire)

    @Column(name = "totale_charge")
    private String totale_charge;

    @Column(name = "totale_revenues")
    private String totale_revenues;
    @Column(name = "totale")
    private String totale;

    @ManyToOne(cascade = CascadeType.ALL)
    Charge charge;
    @ManyToOne(cascade = CascadeType.ALL)
    Revenues revenues;

}
