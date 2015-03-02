package com.cagnosolutions.nei.shipping.signature

import groovy.transform.CompileStatic
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@CompileStatic
@Repository
interface SignatureRepository extends JpaRepository<Signature, Integer> {
}
