package com.store.writers.service.impl;

import com.store.writers.model.entity.BillingAddress;
import com.store.writers.model.entity.UserBilling;
import com.store.writers.service.BillingAddressService;
import org.springframework.stereotype.Service;

@Service
public class BillingAddressServiceImpl implements BillingAddressService {

   public BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress){

       billingAddress.setName(userBilling.getUserBillingName());
       billingAddress.setStreet(userBilling.getUserBillingStreet());
       billingAddress.setCity(userBilling.getUserBillingCity());
       billingAddress.setProvince(userBilling.getUserBillingProvince());
       billingAddress.setCountry(userBilling.getUserBillingCountry());
       billingAddress.setCode(userBilling.getUserBillingCode());
       return billingAddress;
   }

}
