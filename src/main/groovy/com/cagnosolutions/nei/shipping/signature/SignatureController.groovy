package com.cagnosolutions.nei.shipping.signature

import com.cagnosolutions.nei.shipping.slip.Slip
import com.cagnosolutions.nei.shipping.slip.SlipService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping(value = "/secure/signature")
class SignatureController {


	@Autowired
	SignatureService signatureService

	@Autowired
	SlipService slipData

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
	String add(Signature signature, @RequestParam(value = "slipIds", required = false) List<Long> slipIds) {
		signature.completed = new Date()
		signature = signatureService.save signature
		if (slipIds == null) {
			return "redirect:/secure/signature/${signature.id}"
		}
		List<Slip> slips = slipData.findAll(slipIds)
		slips.collect { slip ->
			slip.signature = signature
		}
		slipData.save slips
		"redirect:/"
	}

    @RequestMapping(value="/{id}/view", method = RequestMethod.GET)
    String viewOne(@PathVariable Long id, Model model) {
        model.addAttribute("signature", signatureService.findOne(id))
		"signature/signatureView"
    }


}
