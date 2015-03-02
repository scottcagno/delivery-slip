package com.cagnosolutions.nei.shipping

import groovy.transform.CompileStatic
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@CompileStatic
@Controller
class Authentication {

	@RequestMapping(value = "/login")
	String login() {
		"login"
	}
}
