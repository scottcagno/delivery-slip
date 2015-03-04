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
    Integer id

	@Type(type = "date")
    Date created

	@OneToOne
	Signature signature

	@Column(name="order_num")
	Integer jobNumber

	@Column(columnDefinition = "TINYINT(1)")
	Short complete

	@Column(columnDefinition = "TINYINT(1)")
	Short mobile

	@Column(columnDefinition = "TINYINT(1)")
	Short selected

	@Column(columnDefinition = "longtext")
	String notes

    Integer sort, quantity
    String jobName, po, phone, address, city, state, zip, samples
	String customer, contact, envelope, email, hash, cartons

}
