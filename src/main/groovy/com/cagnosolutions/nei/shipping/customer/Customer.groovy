package com.cagnosolutions.nei.shipping.customer

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * Created by Scott Cagno.
 * Copyright Cagno Solutions. All rights reserved.
 */

@Entity
class Customer {

    @Id
    @GeneratedValue
    Long id
    String company, contact, phone, email
    String street, city, state, zip
}
