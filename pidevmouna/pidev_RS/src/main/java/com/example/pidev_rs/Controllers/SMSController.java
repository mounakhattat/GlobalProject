package com.example.pidev_rs.Controllers;


import com.example.pidev_rs.Model.SMSSendRequest;
import com.example.pidev_rs.Services.SMSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class SMSController {
    @Autowired
    SMSService smsService;
    @PostMapping("/PostSMS")
    public String postSMS(@RequestBody SMSSendRequest sendRequest){
        log.info("postSMS Started SendRequest : " + sendRequest.toString());
        return smsService.sendSMS(sendRequest.getDestinationSMSNumber(),sendRequest.getSmsMessage()) ;

    }


    @GetMapping("/test")
    public String get(){
        return smsService.test();
    }

}