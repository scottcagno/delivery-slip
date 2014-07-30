package com.cagnosolutions.nei.shipping.sig

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * Created by greg on 7/29/14.
 */

@Entity
class Sig {

    @Id
    @GeneratedValue
    Long Id

    Long completed
    String signedBy

	@Column(length = 500000)
    String signature
}
