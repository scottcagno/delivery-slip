package com.cagnosolutions.nei.shipping.signature

import com.cagnosolutions.nei.shipping.slip.Slip
import com.cagnosolutions.nei.shipping.slip.SlipData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping(value = "/signature")
class SignatureController {


	@Autowired
	SignatureData signatureData

	@Autowired
	SlipData slipData

	@RequestMapping(method = RequestMethod.GET)
	String view() {
		"sig/sig"
	}

    @RequestMapping(value="/{id}/view", method = RequestMethod.GET)
    @ResponseBody
    String viewOne(@PathVariable Long id) {
        signatureData.findOne id
    }

	@RequestMapping(method = RequestMethod.POST)
	String add(Signature signature, @RequestParam(value = "slipIds", required = false) List<Long> slipIds) {
		signature.completed = System.currentTimeMillis()
		signature = signatureData.save signature
		if (slipIds == null) {
			return "redirect:/signature/${signature.id}"
		}
		List<Slip> slips = slipData.findAll(slipIds)
		slips.collect { slip ->
			slip.signature = signature
		}
		slipData.save slips
		"redirect:/"
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	String selectSlip(Model model, @PathVariable Long id) {
		model.addAllAttributes([signature: signatureData.findOne(id), slips: slipData.findAllWithoutSig()])
		"sig/sigslip"
	}
}
