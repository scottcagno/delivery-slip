package com.cagnosolutions.nei.shipping.slip

import com.cagnosolutions.nei.shipping.signature.Signature
import groovy.transform.CompileStatic
import org.hibernate.annotations.Type

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToOne

@CompileStatic
@Entity
class Slip {

    @Id
    @GeneratedValue
    Long id

	@Type(type = "date")
    Date created

    /*@OneToOne
    Customer customer*/

    @OneToOne
    Signature signature

	@Column(name="order_num")
	Integer jobNumber

    Integer sort, quantity, cartons, samples
	Short complete, mobile, selected
    String jobName, po, notes, phone, address, city, state, zip
	String customer, contact, envelope, email, hash

}
