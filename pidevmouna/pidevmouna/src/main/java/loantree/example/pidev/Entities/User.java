package loantree.example.pidev.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Transactional
@Entity
@Table(name = "User")

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idUser")
    private Integer idUser;
    private String firstName;
    private String lastName;

    private String username;
    private Integer age;
    private Date birthDate;
    private String placeBirth;
    private String job;
    private String sex;
    private String housing;
    private Integer postalCode;
    private String email;
    private boolean actived;
    private String numPhone;
    private String password;
    private Date dateCreation;




    public User(String username, String email, String encode) {
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Account> Account;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Reclamation> reclamations;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Credit> credits;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Events> events;
    @OneToOne(mappedBy = "UserPass")
    private PasswordResetToken passwordResetToken;
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

                ", Email='" + email + '\'' +

                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", Account=" + Account +
                ", roles=" + roles +
                '}';
    }


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();





}