package com.cagnosolutions.nei.shipping.slip

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

import java.text.SimpleDateFormat

/**
 * Created by Scott Cagno.
 * Copyright Cagno Solutions. All rights reserved.
 */

@Service
class SlipService {

    @Autowired
    SlipRepository repo

    List<Slip> findAll() {
        repo.findAll()
    }

	List<Slip> findAll(Iterable<Long> slipIds) {
		repo.findAll(slipIds)
	}

    Page<Slip> findAll(int page, int size, String... fields) {
        repo.findAll(new PageRequest(page, size, Sort.Direction.ASC, fields))
    }

    List<Slip> findAllForCustomer(Long id) {
        repo.findAllForCustomer(id)
    }

	List<Slip> findAllValid() {
		repo.findAllValid(new Date())
		def format = new SimpleDateFormat("MM/dd/yyyy")
		Date date = format.parse(format.format(new Date()))
	}

    Slip findOne(Long id) {
        repo.findOne(id)
    }

    Slip save(Slip slip) {
        repo.save(slip)
    }

	List<Slip> save(List<Slip> slips) {
		repo.save(slips)
	}

    def delete(Long id) {
        repo.delete(id)
    }

    def delete(Slip slip) {
        repo.delete(slip)
    }
}

@Repository
interface SlipRepository extends JpaRepository<Slip, Long> {
    @Query("SELECT s FROM Slip s WHERE s.customer.id=:id")
    List<Slip> findAllForCustomer(@Param("id") Long id)

	@Query("SELECT s FROM Slip s WHERE s.signature.id=NULL AND s.active=1 AND dayofyear(s.created)=dayofyear(:date)")
	List<Slip> findAllValid(@Param(value = "date") Date date);
}
