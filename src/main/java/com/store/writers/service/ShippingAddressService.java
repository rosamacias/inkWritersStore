package com.store.writers.service;

import com.store.writers.model.entity.ShippingAddress;
import com.store.writers.model.entity.UserShipping;

public interface ShippingAddressService {
    ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress);
}
