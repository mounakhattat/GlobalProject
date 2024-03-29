package loantree.example.pidev.Entities;
import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
@Entity
@Table(name="PasswordToken")
public class PasswordResetToken implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IdPasswordToken")
    private Long IdPasswordToken;
    @Column(name="token")
    private String token;
    @Column(name="expireDate")
    private Instant expireDate;

    @OneToOne(mappedBy = "passwordResetToken")
    private User UserPass;


    public PasswordResetToken() {

    }

    public Long getIdPasswordToken() {
        return IdPasswordToken;
    }

    public void setIdPasswordToken(Long idPasswordToken) {
        IdPasswordToken = idPasswordToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Instant getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Instant expireDate) {
        this.expireDate = expireDate;
    }

    public User getUsertPass() {
        return UserPass;
    }

    public void setUserPass(User usertPass) {
        UserPass = usertPass;
    }

    @Override
    public String toString() {
        return "PasswordResetToken{" +
                "IdPasswordToken=" + IdPasswordToken +
                ", token='" + token + '\'' +
                ", expireDate=" + expireDate +
                ", UsertPass=" + UserPass +
                '}';
    }
}