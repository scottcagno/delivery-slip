package com.cagnosolutions.nei.shipping.user

import groovy.transform.CompileStatic
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

/**
 * Created by greg on 3/4/15.
 */
@CompileStatic
@Repository
interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT COUNT(u.id) FROM User u WHERE u.id<>:id AND u.username=:username")
	int canUpdate(@Param("id") Long id, @Param("username") String username)

	@Query("SELECT u FROM User u WHERE u.username=:username")
	User findOne(@Param("username") String username)
}
