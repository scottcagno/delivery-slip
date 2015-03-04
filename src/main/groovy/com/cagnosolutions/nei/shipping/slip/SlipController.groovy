package com.cagnosolutions.nei.shipping.slip

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
@RequestMapping("/secure/slip")
class SlipController {

    @Autowired
    SlipService slipService

	@RequestMapping(method = RequestMethod.GET)
	String all(Model model, @RequestParam(required = false) Integer page, @RequestParam(required = false) String sort) {
		def slips = slipService.findAll(page? page-1 :0 , 20, sort?:"id")
		page = (page ? page : 1)
		def ub = (((slips.totalPages - page) >= 4) ? page + 4 : slips.totalPages)
		if (page < 6) {
			ub = ((slips.totalPages > 10) ? 10 : slips.totalPages)
		}
		def lb = (((ub - 9) > 0) ? ub - 9 : 1)
		model.addAllAttributes([slips: slips, lb: lb, ub : ub])
		"slip/slip"
	}

    @RequestMapping(method = RequestMethod.POST)
    String edit(Slip slip, RedirectAttributes attr) {
		def existingSlip = slipService.findOne slip.id
		slipService.mergeProperties(slip, existingSlip)
        slipService.save existingSlip
		attr.addFlashAttribute("alertSuccess", "Successfully updated customer")
        "redirect:/secure/slip"
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    String view(Model model, @PathVariable Integer id) {
        model.addAttribute("slip", slipService.findOne(id))
        "slip/slipCustomer"
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    String delete(@PathVariable Integer id) {
        slipService.delete id
        "redirect:/secure/slip"
    }
}