package com.store.writers.service.impl;

import com.store.writers.model.entity.ShippingAddress;
import com.store.writers.model.entity.UserShipping;
import com.store.writers.service.ShippingAddressService;
import org.springframework.stereotype.Service;

@Service
public class ShippingAdressServiceImpl implements ShippingAddressService {

    public ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress)
    {
        shippingAddress.setName(userShipping.getUserShippingName());
        shippingAddress.setStreet(userShipping.getUserShippingStreet());
        shippingAddress.setCity(userShipping.getUserShippingCity());
        shippingAddress.setProvince(userShipping.getUserShippingProvince());
        shippingAddress.setCountry(userShipping.getUserShippingCountry());
        shippingAddress.setCode(userShipping.getUserShippingCode());

        return shippingAddress;
    }

}
