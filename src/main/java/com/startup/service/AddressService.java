package com.startup.service;
import com.startup.dao.AddressDao;
import com.startup.model.Address;

import java.util.List;

public class AddressService {
    private AddressDao addressDao = new AddressDao();

    public boolean addAddress(Address address) {
        return addressDao.saveAddress(address);
    }
    
    public List<Address> getAddressByUserId(int userId) {
        return addressDao.findAddressesByUserId(userId);
    }

    public boolean deleteAddressByUserId(int userId) {
        return addressDao.deleteAddressByUserId(userId);
    }
}