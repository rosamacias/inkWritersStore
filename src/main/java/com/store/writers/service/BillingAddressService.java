package com.store.writers.service;

import com.store.writers.model.entity.BillingAddress;
import com.store.writers.model.entity.UserBilling;

public interface BillingAddressService {

    BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress);

}
