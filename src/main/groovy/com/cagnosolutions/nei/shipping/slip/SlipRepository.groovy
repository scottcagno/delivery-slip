package com.cagnosolutions.nei.shipping.slip
import groovy.transform.CompileStatic
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@CompileStatic
@Repository
interface SlipRepository extends JpaRepository<Slip, Integer> {

	@Query("SELECT s FROM Slip s WHERE s.mobile=1 AND s.complete=0 AND s.created=CURRENT_DATE ORDER BY s.sort")
	List<Slip> findAllValid()
	
	@Query("SELECT s FROM Slip s Where s.hash=:hash")
	Slip findOneByHash(@Param("hash") String hash)

	@Query(nativeQuery = true, value = "select * from slip where created=curdate() and mobile=1 group by sort")
	List<Slip>findDeliveriesNoRepeat()

	@Query(nativeQuery = true, value = "select * from slip where created=curdate() and mobile=1 order by sort")
	List<Slip>findDeliveries()
}
