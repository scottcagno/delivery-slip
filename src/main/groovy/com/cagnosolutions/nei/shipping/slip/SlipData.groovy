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

/**
 * Created by Scott Cagno.
 * Copyright Cagno Solutions. All rights reserved.
 */

@Service
class SlipData {

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

	List<Slip> findAllWithoutSig() {
		repo.findAllWithoutSig()
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

	@Query("SELECT s FROM Slip s WHERE s.signature.id=NULL")
	List<Slip> findAllWithoutSig();
}
