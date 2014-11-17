package com.cagnosolutions.nei.shipping.signature

import groovy.transform.CompileStatic
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@CompileStatic
@Entity
class Signature {

    @Id
    @GeneratedValue
    Long id

    Date completed

    String signedBy

	@Column(length = 500000)
    String signature
}
