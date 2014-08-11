package com.cagnosolutions.nei.shipping.mail

import com.cagnosolutions.nei.shipping.slip.SlipService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Created by greg on 8/8/14.
 */

@Controller
class testController {

	@Autowired
	SlipService slipService

	@Autowired
	MailService mailService

	@RequestMapping("/mail")
	@ResponseBody
	String mail() {
		def slip = slipService.findOne(1L)
		Map<String, Object> model = new HashMap()
		model.put("word", "testing");
		try{
			mailService.sendMimeMail("test@test.com", "Test Mail", "test.ftl", model, "gregpechiro@gmail.com")
		} catch (Exception e) {
			e.printStackTrace()
			return "Mail NOT Sent"
		}
		"Mail Sent"
	}
}
