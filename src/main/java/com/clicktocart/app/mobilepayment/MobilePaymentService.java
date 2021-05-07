package com.clicktocart.app.mobilepayment;

import com.clicktocart.app.payload.response.CartResponse;
import com.clicktocart.app.repository.CartRepository;
import com.clicktocart.app.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class MobilePaymentService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ItemService itemService;

    public String chargeMobilePayment(int userID, List<CartResponse> cartResponseList){
        Date date= new Date();
        long time = date.getTime();
        Timestamp curentTime = new Timestamp(time);

        cartRepository.updateCartPaymentSucess(userID,curentTime);

        //Reduce stock when customer paid
        for(CartResponse cartResponse:cartResponseList){
            itemService.updateStockCustomer(cartResponse.getQty(),cartResponse.getItemId());
        }

        return "success";
    }

}
