package com.cagnosolutions.nei.shipping.mail

import com.cagnosolutions.nei.shipping.slip.SlipService
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@CompileStatic
@Controller
class MainTestController {

	@Autowired
	SlipService slipService

	@Autowired
	MailService mailService

	@RequestMapping("/mail")
	@ResponseBody
	String mail() {
		def slip = slipService.findOne(200L)
		Map<String, Object> model = new HashMap()
		model.put("slip", slip);
		try{
			mailService.sendMimeMail("test@test.com", "Test Mail", "test.ftl", model, "gregpechiro@gmail.com")
		} catch (Exception e) {
			e.printStackTrace()
			return "Mail NOT Sent"
		}
		"Mail Sent"
	}
}
