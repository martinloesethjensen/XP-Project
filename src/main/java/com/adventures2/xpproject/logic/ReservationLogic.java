package com.adventures2.xpproject.logic;

import com.adventures2.xpproject.base.Customer;
import com.adventures2.xpproject.base.Reservation;

public class ReservationLogic {
    public static void create(Reservation reservation, Customer customer) {
        int id = 0;

        //If a new customer is made
        if(customer.getId() == 0)
            id = CustomerLogic.create(customer);


    }
}
