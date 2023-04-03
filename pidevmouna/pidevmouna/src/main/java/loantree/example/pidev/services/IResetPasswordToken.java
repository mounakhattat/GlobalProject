package loantree.example.pidev.services;

import loantree.example.pidev.Entities.PasswordResetToken;
import loantree.example.pidev.Exception.UserNotFoundException;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IResetPasswordToken {
    PasswordResetToken CreatePasswordToken(String email) throws UserNotFoundException, UnsupportedEncodingException, MessagingException;
    boolean VerifyExpiration(String token);
    void ConfirmPasswordReset(String token,String password);

    List<PasswordResetToken> getExpireToken();

    void deleteToken(Long id);
}
