package com.cagnosolutions.nei.shipping.slip

import com.cagnosolutions.nei.shipping.user.User

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

    @OneToOne
    User customer

    String signedBy, signature

}
