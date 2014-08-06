package com.cagnosolutions.nei.shipping.slip
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
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

	Page<Slip> findAll(Pageable pageable) {
		repo.findAll(pageable)
	}

	List<Slip> findAll(String sort, String order) {
		if ((sort == null && order == null) || sort == null) {
			return repo.findAll()
		}
		if (order == null || !order.toLowerCase().startsWith("asc") || !order.toLowerCase().startsWith("desc")) {
			return  repo.findAll(new Sort(Sort.Direction.fromString("ASC"), sort))
		}
		repo.findAll(new Sort(Sort.Direction.fromString(order), sort))
	}

    List<Slip> findAllForCustomer(Long id) {
        repo.findAllForCustomer(id)
    }

	List<Slip> findAllValid() {
		repo.findAllValid()
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

	@Query("SELECT s FROM Slip s WHERE s.active=1 AND s.signature=NULL AND s.created=CURRENT_DATE")
	List<Slip> findAllValid()
}
