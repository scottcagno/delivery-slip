package com.cagnosolutions.nei.shipping.slip
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@CompileStatic
@Service
class SlipService {

    @Autowired
    SlipRepository repo

    List<Slip> findAll() {
        repo.findAll()
    }

	List<Slip> findAll(Iterable<Integer> slipIds) {
		repo.findAll(slipIds)
	}

    Page<Slip> findAll(int page, int size, String... fields) {
        repo.findAll(new PageRequest(page, size, Sort.Direction.ASC, fields))
    }

	/*Page<Slip> findAll(Pageable pageable) {
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

    List<Slip> findAllForCustomer(Integer id) {
        repo.findAllForCustomer(id)
    }*/

	List<Slip> findAllValid() {
		repo.findAllValid()
	}

    Slip findOne(Integer id) {
        repo.findOne(id)
    }

    Slip save(Slip slip) {
        repo.save(slip)
    }

	List<Slip> save(List<Slip> slips) {
		repo.save(slips)
	}

    def delete(Integer id) {
        repo.delete(id)
    }

    def delete(Slip slip) {
        repo.delete(slip)
    }

	// helper method
	static def mergeProperties(modified, original) {
		modified.properties.each { key, value ->
			if (original.hasProperty(key as String) && !((key as String) in ['class', 'metaClass']) && value != null)
				original[key as String] = value
		}
	}
}

