package com.clicktocart.app.controller;

import com.clicktocart.app.model.CartRecords;
import com.clicktocart.app.payload.response.CartResponse;
import com.clicktocart.app.stripeclient.StripeClient;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/payment")
public class PaymentGatewayController {
    private StripeClient stripeClient;

    @Autowired
    PaymentGatewayController(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }

    @PostMapping("/charge")
    public Charge chargeCard(@RequestHeader(value="token") String token, @RequestHeader(value="amount") Double amount, @RequestHeader(value="userid") Integer userid, @RequestBody List<CartResponse> cartResponseList) throws Exception {

        return this.stripeClient.chargeNewCard(token, amount, userid, cartResponseList);

    }
}
