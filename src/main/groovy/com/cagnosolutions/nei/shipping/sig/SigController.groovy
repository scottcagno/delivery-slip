package com.cagnosolutions.nei.shipping.sig

import com.cagnosolutions.nei.shipping.slip.Slip
import com.cagnosolutions.nei.shipping.slip.SlipData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

/**
 * Created by greg on 7/29/14.
 */

@Controller
@RequestMapping(value = "/signature")
class SigController {


	@Autowired
	SigData sigData

	@Autowired
	SlipData slipData

	@RequestMapping(method = RequestMethod.GET)
	String view() {
		"sig/sig"
	}

	@RequestMapping(method = RequestMethod.POST)
	String add(Sig sig, @RequestParam(value = "slipIds", required = false) List<Long> slipIds) {
		sig.completed = System.currentTimeMillis()
		sig = sigData.save sig
		if (slipIds == null) {
			return "redirect:/signature/${sig.id}"
		}
		List<Slip> slips = slipData.findAll(slipIds)
		slips.collect { slip ->
			slip.sig = sig
		}
		slipData.save slips
		"redirect:/"
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	String selectSlip(Model model, @PathVariable Long id) {
		model.addAllAttributes([sig: sigData.findOne(id), slips: slipData.findAllWithoutSig()])
		"sig/sigSlip"
	}
}
