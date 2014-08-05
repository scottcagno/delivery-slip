package com.cagnosolutions.nei.shipping.slip
import com.cagnosolutions.nei.shipping.customer.CustomerService
import com.cagnosolutions.nei.shipping.signature.SignatureService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
/**
 * Created by Scott Cagno.
 * Copyright Cagno Solutions. All rights reserved.
 */

@Controller
@RequestMapping("/secure/slip")
class SlipController {

    @Autowired
    SlipService slipData

    @Autowired
    CustomerService customerData

    @Autowired
    SignatureService signatureData

    @RequestMapping(method = RequestMethod.GET)
    String all(Model model) {
        model.addAttribute "slips", slipData.findAll()
        "slip/slip"
    }

    @RequestMapping(method = RequestMethod.POST)
    String addOrEdit(Slip slip) {
        if(slip.customer != null)
            slip.customer = customerData.findOne(slip.customer.id)
        if(slip.signature != null)
            slip.signature = signatureData.findOne(slip.signature.id)
		Date date = new Date()
        slipData.save slip
        "redirect:/secure/slip"
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    String view(Model model, @PathVariable Long id) {
        model.addAllAttributes([slips:slipData.findAll(), slip:slipData.findOne(id)])
        "slip/slip"
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    String delete(@PathVariable Long id) {
        slipData.delete id
        "redirect:/secure/slip"
    }
}
