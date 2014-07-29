package com.cagnosolutions.nei.shipping.user

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

/**
 * Created by Scott Cagno.
 * Copyright Cagno Solutions. All rights reserved.
 */

@Entity
@Table(name="user")
class User {

    @Id
    @GeneratedValue
    Long id
    String name, email, username, password, role = "ROLE_USER"
    Long creation, lastSeen
    short active = 1
}