package com.cagnosolutions.nei.shipping

import com.cagnosolutions.nei.shipping.slip.SlipService
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@CompileStatic
@Controller
class IndexController {

	@Autowired
	SlipService slipService
	
    @RequestMapping(value = "/", method = RequestMethod.GET)
    String index() {
        "index"
    }
	
	@RequestMapping(value = "/slip/{hash}", method = RequestMethod.GET)
	String slip(@PathVariable String hash, Model model) {
		def slip = slipService.findOneByHash hash
		model.addAttribute("slip", slip)
		"slip/customerView"
	}
	
	@RequestMapping(value = "/sig/{hash}", method = RequestMethod.GET)
	String signature(@PathVariable String hash, Model model) {
		def slip = slipService.findOneByHash hash
		model.addAttribute("signature", slip.signature.signature_bin)
		"signature/customerView"
	}
}


