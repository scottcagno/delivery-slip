package com.cagnosolutions.nei.shipping.slip
import com.cagnosolutions.nei.shipping.customer.Customer
import com.cagnosolutions.nei.shipping.signature.Signature
import groovy.transform.CompileStatic
import org.hibernate.annotations.Type

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

    @OneToOne
    Customer customer

    @OneToOne
    Signature signature

    Integer sort, jobNumber, quantity, cartons, samples, active
    String jobName, po, notes

}
