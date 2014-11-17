package com.cagnosolutions.nei.shipping.customer

import groovy.transform.CompileStatic

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@CompileStatic
@Entity
class Customer {

    @Id
    @GeneratedValue
    Long id
    String company, contact, phone, email
    String street, city, state, zip
}
