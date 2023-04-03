package loantree.example.pidev.Model;

import lombok.Data;

@Data
public class SMSSendRequest {
    private String destinationSMSNumber;
    private String smsMessage;

}
