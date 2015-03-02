package com.cagnosolutions.nei.shipping.customer

import groovy.transform.CompileStatic
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@CompileStatic
@Repository
interface CustomerRepository extends JpaRepository<Customer, Long> {

}
