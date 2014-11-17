package com.cagnosolutions.nei.shipping.signature

import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@CompileStatic
@Service
class SignatureService {

    @Autowired
    SignatureRepository repo

    List<Signature> findAll() {
        repo.findAll()
    }

    Page<Signature> findAll(int page, int size, String... fields) {
        repo.findAll(new PageRequest(page, size, Sort.Direction.ASC, fields))
    }

    Signature findOne(Long id) {
        repo.findOne(id)
    }

    Signature save(Signature sig) {
        repo.save(sig)
    }

    def delete(Long id) {
        repo.delete(id)
    }

    def delete(Signature sig) {
        repo.delete(sig)
    }

}

@CompileStatic
@Repository
interface SignatureRepository extends JpaRepository<Signature, Long> {
}
