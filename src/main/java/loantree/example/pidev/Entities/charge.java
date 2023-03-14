package loantree.example.pidev.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "charge")
public class charge implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_charge")
    private Long id; // Identifiant  (Clé primaire)

    @Column(name = "type_charge")
    private String type_charge;

    @Column(name = "somme_charge")
    private String somme_charge;

    public charge(Long id) {
        this.id = id;
    }
    @OneToMany(cascade = CascadeType.ALL, mappedBy="charge")
    private Set<accounting>  accountings;

    public charge() {

    }
}
