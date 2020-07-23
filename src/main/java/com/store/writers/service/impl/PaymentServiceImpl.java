package com.store.writers.service.impl;

import com.store.writers.model.entity.Payment;
import com.store.writers.model.entity.UserPayment;
import com.store.writers.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    public Payment setByUserPayment(UserPayment userPayment, Payment payment)
    {
        payment.setType(userPayment.getType());
        payment.setCardName(userPayment.getCardName());
        payment.setCardNumber(userPayment.getCardNumber());
        payment.setHolderName(userPayment.getHolderName());
        payment.setCvc(userPayment.getCvc());
        payment.setExpireMounth(userPayment.getExpireMounth());
        payment.setGetExpireYear(userPayment.getExpireYear());

        return payment;
    }

}
