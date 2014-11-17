package com.cagnosolutions.nei.shipping.user

import groovy.transform.CompileStatic

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@CompileStatic
@Entity
@Table(name="user")
class User {

    @Id
    @GeneratedValue
    Long id
    String name, email, username, password, role = "ROLE_USER"
    short active = 1
}