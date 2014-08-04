package com.cagnosolutions.nei.shipping.customer

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

/**
 * Created by Scott Cagno.
 * Copyright Cagno Solutions. All rights reserved.
 */

@Service
class CustomerData {

    @Autowired
    CustomerRepository repo

    List<Customer> findAll() {
        repo.findAll()
    }

    Customer findOne(Long id) {
        repo.findOne(id)
    }

    Customer save(Customer customer) {
        repo.save(customer)
    }

    def delete(Long id) {
        repo.delete(id)
    }

    def delete(Customer customer) {
        repo.delete(customer)
    }

}

interface CustomerRepository extends JpaRepository<Customer, Long> {

}
