package com.cagnosolutions.nei.shipping.slip

import com.cagnosolutions.nei.shipping.customer.CustomerService
import com.cagnosolutions.nei.shipping.signature.SignatureService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

/**
 * Created by Scott Cagno.
 * Copyright Cagno Solutions. All rights reserved.
 */

@Controller
@RequestMapping("/secure/slip")
class SlipController {

    @Autowired
    SlipService slipService

    @Autowired
    CustomerService customerService

    @Autowired
    SignatureService signatureService

    /*@RequestMapping(method = RequestMethod.GET)
    String all(Model model, @RequestParam(required = false) String sort, @RequestParam(required = false) String order) {
		model.addAttribute("slips", slipData.findAll(sort, order))
        "slip/slip"
    }*/

	@RequestMapping(method = RequestMethod.GET)
	Object all(Model model, @RequestParam(required = false) Integer page, @RequestParam(required = false) String sort) {
		def slips = slipService.findAll(page? page-1 :0 , 20, sort?:"id")
		page = (page? page :1)
		def ub = (((slips.totalPages - page) >= 4)? page + 4 : slips.totalPages)
		if (page < 6) {
			ub = ((slips.totalPages > 10)? 10 : slips.totalPages)
		}
		def lb = (((ub - 9) > 0)? ub-9: 1)
		model.addAllAttributes([slips: slips, lb: lb, ub : ub])
		"slip/slip"
	}
    @RequestMapping(method = RequestMethod.POST)
    String addOrEdit(Slip slip) {
        if(slip.customer != null)
            slip.customer = customerService.findOne(slip.customer.id)
        if(slip.signature != null)
            slip.signature = signatureService.findOne(slip.signature.id)
		Date date = new Date()
        slipService.save slip
        "redirect:/secure/slip"
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    String view(Model model, @PathVariable Long id) {
        model.addAllAttributes([slips:slipService.findAll(), slip:slipService.findOne(id)])
        "slip/slip"
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    String delete(@PathVariable Long id) {
        slipService.delete id
        "redirect:/secure/slip"
    }
}
