package com.clicktocart.app.stripeclient;

import com.clicktocart.app.payload.response.CartResponse;
import com.clicktocart.app.repository.CartRepository;
import com.clicktocart.app.repository.ItemRepository;
import com.clicktocart.app.services.ItemService;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StripeClient {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ItemService itemService;

    @Autowired
    StripeClient() {
        Stripe.apiKey = "sk_test_51ImBWIFPpVy9RtnRW477vfYcyEXVyeoVhmTCrf8EYoz3uEEGRtR2l4UKiZj59fUd8gJQzqh41Z4rDHrqKsnVLjFR00eOkwcqpV";
    }

    public Customer createCustomer(String token, String email) throws Exception {
        Map<String, Object> customerParams = new HashMap<String, Object>();
        customerParams.put("email", email);
        customerParams.put("source", token);
        return Customer.create(customerParams);
    }

    private Customer getCustomer(String id) throws Exception {
        return Customer.retrieve(id);
    }

    public Charge chargeNewCard(String token, double amount, int userID, List<CartResponse> cartResponseList) throws Exception {
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", (int)(amount * 100));
        chargeParams.put("currency", "USD");
        chargeParams.put("source", token);
        Charge charge = Charge.create(chargeParams);

        Date date= new Date();
        long time = date.getTime();
        Timestamp curentTime = new Timestamp(time);

        cartRepository.updateCartPaymentSucess(userID,curentTime);

        //Reduce stock when customer paid
        for(CartResponse cartResponse:cartResponseList){
            itemService.updateStockCustomer(cartResponse.getQty(),cartResponse.getItemId());
        }

        return charge;
    }

    public Charge chargeCustomerCard(String customerId, int amount) throws Exception {
        String sourceCard = getCustomer(customerId).getDefaultSource();
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", amount);
        chargeParams.put("currency", "USD");
        chargeParams.put("customer", customerId);
        chargeParams.put("source", sourceCard);
        Charge charge = Charge.create(chargeParams);
        return charge;
    }
}
