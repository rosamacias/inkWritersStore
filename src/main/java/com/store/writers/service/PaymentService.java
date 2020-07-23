package com.store.writers.service;

import com.store.writers.model.entity.Payment;
import com.store.writers.model.entity.UserPayment;

public interface PaymentService {
    Payment setByUserPayment(UserPayment userPayment, Payment payment);
}
