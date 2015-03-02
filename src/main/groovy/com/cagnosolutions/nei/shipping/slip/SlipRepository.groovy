package com.cagnosolutions.nei.shipping.slip
import groovy.transform.CompileStatic
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@CompileStatic
@Repository
interface SlipRepository extends JpaRepository<Slip, Long> {
	/*@Query("SELECT s FROM Slip s WHERE s.customer.id=:id")
	List<Slip> findAllForCustomer(@Param("id") Long id)*/

	@Query("SELECT s FROM Slip s WHERE s.active=1 AND s.signature=NULL AND s.created=CURRENT_DATE")
	List<Slip> findAllValid()
}
