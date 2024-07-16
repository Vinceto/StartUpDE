package com.startup.service;
import com.startup.dao.AddressDao;
import com.startup.model.Address;

public class AddressService {
    private AddressDao addressDao = new AddressDao();

    public boolean addAddress(Address address) {
        return addressDao.saveAddress(address);
    }
}