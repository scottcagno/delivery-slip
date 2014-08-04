package com.cagnosolutions.nei.shipping.signature

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Signature {

    @Id
    @GeneratedValue
    Long Id

    Long completed
    String signedBy

	@Column(length = 500000)
    String signature
}
