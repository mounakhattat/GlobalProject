package loantree.example.pidev.Entities;

import java.io.Serializable;

@Entity
@Table(name = "revenues")
public class revenues implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_charge")
    private Long id; // Identifiant  (Clé primaire)

    @Column(name = "type_revenues")
    private String type_revenues;

    @Column(name = "somme_revenues")
    private String somme_revenues;

    public revenues(Long id) {
        this.id = id;
    }
    @OneToMany(cascade = CascadeType.ALL, mappedBy="revenues")
    private Set<Accounting> accountings;
    }
