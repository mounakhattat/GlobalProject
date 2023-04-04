package loantree.example.pidev.Entities;

<<<<<<< HEAD:pidevmouna/pidevmouna/pidevmouna/src/main/java/loantree/example/pidev/Entities/charge.java
import java.io.Serializable;

=======
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
@AllArgsConstructor
>>>>>>> 5cef2ce9968aaedb64b85496dedc62258d102de0:pidevmouna/pidevmouna/src/main/java/loantree/example/pidev/Entities/charge.java
@Entity
@Table( name = "charge")
public class Charge implements Serializable {  // convertir la forme l'objet en  byte pour la protection du data //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idCharge")
    private Integer idCharge; // Clé primaire
    private Long sommeCharge;
    private String TypeCharge;

    @ManyToOne()
    @JoinColumn(name = "accounting_id")
    private Accounting accounting;
  public Charge (){}

    public void setIdCharge(Integer idCharge) {
        this.idCharge = idCharge;
    }
<<<<<<< HEAD:pidevmouna/pidevmouna/pidevmouna/src/main/java/loantree/example/pidev/Entities/charge.java
    @OneToMany(cascade = CascadeType.ALL, mappedBy="charge")
    private Set<Accounting> accountings;
=======

    public Integer getIdCharge() {
        return idCharge;
    }

    public Long getSommeCharge() {
        return sommeCharge;
    }

    public Accounting getAccounting() {
        return accounting;
    }
    @Column(name = "amount")
    private Double amount;
    public String getTypeCharge() {
        return TypeCharge;
    }

    public Double getAmount() {
        return amount;
    }

    public void setTypeCharge(String typeCharge) {
        TypeCharge = typeCharge;
    }

    public void setAccounting(Accounting accounting) {
        this.accounting = accounting;
    }

    public void setSommeCharge(Long sommeCharge) {
        this.sommeCharge = sommeCharge;
    }





    // Constructeur et accesseurs (getters) et mutateurs (setters)




>>>>>>> 5cef2ce9968aaedb64b85496dedc62258d102de0:pidevmouna/pidevmouna/src/main/java/loantree/example/pidev/Entities/charge.java
}


