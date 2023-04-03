package loantree.example.pidev.Request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
public class SmsRequest {
    private String phoneNumber;
    private String message;
}
