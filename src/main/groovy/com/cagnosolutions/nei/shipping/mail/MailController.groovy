package com.cagnosolutions.nei.shipping.mail

import com.cagnosolutions.nei.shipping.signature.SignatureService
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@CompileStatic
@Controller
class MailController {

	@Autowired
	SignatureService signatureService

	@RequestMapping(value = "/signature/{id}/mail", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	String email(@PathVariable Long id) {
		def sig =  signatureService.findOne(id).signature
		"<img src=\"${sig}\">"
	}
}
