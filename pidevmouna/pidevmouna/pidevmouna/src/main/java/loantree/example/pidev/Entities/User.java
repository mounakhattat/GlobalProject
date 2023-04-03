package loantree.example.pidev.Entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table( name = "User")
public class User implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="idUser")
    private Integer idUser;
    private String FirstName;
    private String LastName;
    private String Email;
    private String Password;
    private Integer Phone;
    private Integer Age;
    private String Image;
    @Enumerated
    private Roles role;
    private String Username;
    @ManyToOne(cascade = CascadeType.ALL)
    Role roles;

    @OneToOne
    private Account account;


    public Integer getIdUser() {
        return idUser;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public Integer getPhone() {
        return Phone;
    }

    public Integer getAge() {
        return Age;
    }

    public String getImage() {
        return Image;
    }

    public Roles getRole() {return role;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setPhone(Integer phone) {
        Phone = phone;
    }

    public void setAge(Integer age) {
        Age = age;
    }

    public void setImage(String image) {
        Image = image;
    }

    public void setRole(Roles role) {this.role = role;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }





    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                ", Phone=" + Phone +
                ", Age=" + Age +
                ", Image='" + Image + '\'' +
                ", job='" + role + '\'' +
                ", Username='" + Username ;
    }
// Constructeur et accesseurs (getters) et mutateurs (setters)

}

