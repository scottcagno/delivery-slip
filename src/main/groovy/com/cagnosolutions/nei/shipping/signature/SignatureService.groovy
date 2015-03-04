package com.cagnosolutions.nei.shipping.signature
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@CompileStatic
@Service
class SignatureService {

    @Autowired
    SignatureRepository repo

    List<Signature> findAll() {
        repo.findAll()
    }

    Signature findOne(Integer id) {
        repo.findOne id
    }

    Signature save(Signature signature) {
        repo.save signature
    }

    def delete(Integer id) {
        repo.delete id
    }

    def delete(Signature signature) {
        repo.delete signature
    }

}

