package com.cagnosolutions.nei.shipping.sig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
/**
 * Created by Scott Cagno.
 * Copyright Cagno Solutions. All rights reserved.
 */

@Service
class SigData {

    @Autowired
    SigRepository repo

    List<Sig> findAll() {
        repo.findAll()
    }

    Page<Sig> findAll(int page, int size, String... fields) {
        repo.findAll(new PageRequest(page, size, Sort.Direction.ASC, fields))
    }

    Sig findOne(Long id) {
        repo.findOne(id)
    }

    Sig save(Sig sig) {
        repo.save(sig)
    }

    def delete(Long id) {
        repo.delete(id)
    }

    def delete(Sig sig) {
        repo.delete(sig)
    }

}

@Repository
interface SigRepository extends JpaRepository<Sig, Long> {
}
