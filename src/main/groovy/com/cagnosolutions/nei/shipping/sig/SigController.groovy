package com.cagnosolutions.nei.shipping.sig

import com.cagnosolutions.nei.shipping.slip.SlipData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

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
	String add(Sig sig, @RequestParam(value = "slipsIds") List<Long> slipIds) {
		sig.completed = System.currentTimeMillis()
		Sig sig2 = sigData.save sig
		if (slipIds == null) {
			"redirect:/signature/" + sig2.getId()
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	String selectSlip(Model model, @PathVariable Long id) {
		model.addAllAttributes([sig: sigData.findOne(id), slips: slipData.findAll()])
		"sig/sigSlip"
	}
}
