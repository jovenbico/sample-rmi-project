package com.bicjo.rmi.module;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.bicjo.rmi.module.object.Address;

public interface AddressService extends Remote {

	void addAddress(String street, String city, String zip) throws RemoteException;

	List<Address> searchStreet(String street) throws RemoteException;

}
