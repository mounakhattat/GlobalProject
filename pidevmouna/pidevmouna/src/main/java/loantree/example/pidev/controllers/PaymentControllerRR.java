package loantree.example.pidev.controllers;

/*

import javax.servlet.http.HttpServletRequest;

import loantree.example.pidev.Entities.Charge;
import loantree.example.pidev.services.StripeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentControllerRR {

    private StripeClient stripeClient;

    @Autowired
    PaymentControllerRR(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }

    @PostMapping("/charge")
    public Charge chargeCard(HttpServletRequest request) throws Exception {
        String token = request.getHeader("token");
        Double amount = Double.parseDouble(request.getHeader("amount"));
        return this.stripeClient.chargeNewCard(token, amount);
    }
}*/
