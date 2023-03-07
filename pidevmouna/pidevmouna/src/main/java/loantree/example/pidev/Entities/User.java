package loantree.example.pidev.Entities;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "User")

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser")
    private Integer idUser;
    private String FirstName;
    private String LastName;
    private Date BirthDate;
    private String PlaceBirth;
    private String job;
    private String Gender;
    private String Housing;
    private Integer PostalCode;
    private String Email;
    private boolean isActived;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Account> Account;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Reclamation> reclamations;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Credit> credits;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Events> events;

    public User() {

    }

    public void setEmail(String email) {
        Email = email;
    }

    private String PhoneNumber;
    private String password;

    private String username;

    public User(String username, String email, String encode) {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", BirthDate=" + BirthDate +
                ", PlaceBirth='" + PlaceBirth + '\'' +
                ", Job='" + job + '\'' +
                ", Gender='" + Gender + '\'' +
                ", Housing='" + Housing + '\'' +
                ", PostalCode=" + PostalCode +
                ", Email='" + Email + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", Account=" + Account +
                ", roles=" + roles +
                '}';
    }


    public Integer getIdUser() {
        return idUser;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public String getPlaceBirth() {
        return PlaceBirth;
    }

    public String getJob() {
        return job;
    }

    public String getGender() {
        return Gender;
    }

    public String getHousing() {
        return Housing;
    }

    public Integer getPostalCode() {
        return PostalCode;
    }

    public String getEmail() {
        return Email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getPassword() {
        return password;
    }


    public Set<Account> getAccount() {
        return Account;
    }

    public boolean isActivated() {
        return isActived;
    }

    public void setActivated(boolean isActived) {
        isActived = isActived;
    }




}