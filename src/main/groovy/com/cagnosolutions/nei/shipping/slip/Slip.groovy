package com.cagnosolutions.nei.shipping.slip
import com.cagnosolutions.nei.shipping.customer.Customer
import com.cagnosolutions.nei.shipping.signature.Signature

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToOne

/**
 * Created by Scott Cagno.
 * Copyright Cagno Solutions. All rights reserved.
 */

@Entity
class Slip {

    @Id
    @GeneratedValue
    Long id

    Date created

    @OneToOne
    Customer customer

    @OneToOne
    Signature signature

    Integer sort, jobNumber, quantity, cartons, samples, complete, active
    String jobName, po, notes

}
