package loantree.example.pidev.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.management.relation.Role;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table( name = "Account")
public class Account implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="idAccount")

    private Integer idAcc;
    private Integer NumAccount;
    private Date DateCreation;
    private Integer Rib;
    private Integer AmountTrans;
    @OneToOne(mappedBy = "account")
    public User user;

    public Integer getIdAcc() {
        return idAcc;
    }

    public void setIdAcc(Integer idAcc) {
        this.idAcc = idAcc;
    }

    public Integer getNumAccount() {
        return NumAccount;
    }

    public void setNumAccount(Integer numAccount) {
        NumAccount = numAccount;
    }

    public Date getDateCreation() {
        return DateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        DateCreation = dateCreation;
    }

    public Integer getRib() {
        return Rib;
    }

    public void setRib(Integer rib) {
        Rib = rib;
    }

    public Integer getAmountTrans() {
        return AmountTrans;
    }

    public void setAmountTrans(Integer amountTrans) {
        AmountTrans = amountTrans;

    }
}

