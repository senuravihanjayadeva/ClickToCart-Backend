package com.clicktocart.app.payload.request;

import lombok.Data;

//class for pass CheckAnswer Object
@Data
public class CheckAnswer {

    private int user_id;

    private int question_pool_id;

    private int question_id;

    private int answered_id;


}
