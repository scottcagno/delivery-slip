package com.cagnosolutions.nei.shipping.signature

import com.cagnosolutions.nei.shipping.mail.MailService
import com.cagnosolutions.nei.shipping.slip.Slip
import com.cagnosolutions.nei.shipping.slip.SlipService
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@CompileStatic
@Controller
@RequestMapping(value = "/secure/signature")
class SignatureController {


	@Autowired
	SignatureService signatureService

	@Autowired
	SlipService slipData

	@Autowired
	MailService mailService

	@RequestMapping(method = RequestMethod.GET)
	String view() {
		"signature/signature"
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	String selectSlip(Model model, @PathVariable Long id) {
		model.addAllAttributes([signature: signatureService.findOne(id), slips: slipData.findAllValid()])
		"signature/signatureSlip"
	}

	@RequestMapping(method = RequestMethod.POST)
	String add(Signature signature, @RequestParam(value = "slipIds", required = false) List<Long> slipIds, RedirectAttributes attr) {
		signature.completed = new Date()
		signature = signatureService.save signature
		if (slipIds == null) {
			return "redirect:/secure/signature/${signature.id}"
		}
		List<Slip> slips = slipData.findAll(slipIds)
		def emails = new ArrayList<String>()
		Map map = new HashMap()
		slips.collect { slip ->
			slip.signature = signature
			def savedSlip = slipData.save slip
			map.put("slip", savedSlip)
			mailService.sendMimeMail("test@test.com", "Slip Accepted", "mail/signed.ftl", map, slip.customer.email)
			emails.add(slip.customer.email)
		}
		attr.addFlashAttribute("emails", emails)
		"redirect:/"
	}

    @RequestMapping(value = "/{id}/view", method = RequestMethod.GET)
    String viewOne(@PathVariable Long id, Model model) {
        model.addAttribute("signature", signatureService.findOne(id))
		"signature/signatureView"
    }
}
