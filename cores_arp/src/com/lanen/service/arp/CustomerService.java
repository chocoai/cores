package com.lanen.service.arp;

import java.util.List;
import java.util.Map;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Customer;

public interface CustomerService extends BaseLongDao<Customer> {
   String getAddressById(Long id);
   List<Map<String, String>> getAddressMap();
}
