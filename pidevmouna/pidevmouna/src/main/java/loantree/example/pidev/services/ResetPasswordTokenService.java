package loantree.example.pidev.services;
import loantree.example.pidev.Entities.PasswordResetToken;
import loantree.example.pidev.Entities.User;
import loantree.example.pidev.Exception.TokenEmailException;
import loantree.example.pidev.Exception.UserNotFoundException;
import loantree.example.pidev.repository.ResetPasswordRepository;
import loantree.example.pidev.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ResetPasswordTokenService implements IResetPasswordToken {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResetPasswordRepository resetPasswordRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private UserService userService;


    @Value("${pass.PassTokenDurationMs}")
    private long passTokenDurationMs;
    private final String FromAddress = "mohamed.hcini@esprit.tn";
    private final String SenderName = "LOANTREE Team";


    @Override
    public PasswordResetToken CreatePasswordToken(String email) throws UserNotFoundException, UnsupportedEncodingException, MessagingException {

        PasswordResetToken passwordResetToken = new PasswordResetToken();
        User user = userRepository.findByEmail(email);
        passwordResetToken.setExpireDate(Instant.now().plusSeconds(passTokenDurationMs));
        passwordResetToken.setToken(UUID.randomUUID().toString());
        String toAddress;
        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"[[URL]]\" >Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        if (user != null) {
            passwordResetToken.setUserPass(user);
            resetPasswordRepository.save(passwordResetToken);
            toAddress = user.getEmail();
            String verifyURL = "http://localhost:8083/api/auth/resetPassword/" + passwordResetToken.getToken();
            content = content.replace("[[URL]]", verifyURL);

        } else
            throw new UserNotFoundException("Could not find any user with the email" + email);

        resetPasswordRepository.save(passwordResetToken);
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(FromAddress, SenderName);
        helper.setTo(toAddress);
        helper.setSubject("Password Reset");
        helper.setText(content, true);

        emailSender.send(message);

        return passwordResetToken;

    }

    @Override
    public boolean VerifyExpiration(String token) {
        PasswordResetToken passwordResetToken = resetPasswordRepository.findByToken(token);
        if (passwordResetToken != null) {
            if (passwordResetToken.getExpireDate().isBefore(Instant.now())) {
                resetPasswordRepository.delete(passwordResetToken);

                throw new TokenEmailException(token, "this token was expired");
            }
            return true;
        } else
            throw new TokenEmailException(token, "this token is not in the database");
    }

    @Override
    public void ConfirmPasswordReset(String token, String password) {
        PasswordResetToken passwordResetToken = resetPasswordRepository.findByToken(token);
        System.out.print(passwordResetToken);
        VerifyExpiration(token);
        User user = passwordResetToken.getUsertPass();

        if (user != null) {
            userService.UpdatePassword(user, password);
        }


    }

    @Override
    public List<PasswordResetToken> getExpireToken() {

        return resetPasswordRepository.findExpireToken(Instant.now());
    }

    @Override
    public void deleteToken(Long id) {
        resetPasswordRepository.deleteById(id);

    }
}