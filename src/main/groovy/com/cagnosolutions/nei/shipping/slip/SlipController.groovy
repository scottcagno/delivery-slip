package com.cagnosolutions.nei.shipping.slip

import com.cagnosolutions.nei.shipping.user.UserData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

/**
 * Created by Scott Cagno.
 * Copyright Cagno Solutions. All rights reserved.
 */

@Controller
@RequestMapping("/secure/slip")
class SlipController {

    @Autowired
    SlipData slipData

    @Autowired
    UserData userData

    // view all (get)
    @RequestMapping(method = RequestMethod.GET)
    String all(Model model) {
        model.addAttribute "slips", slipData.findAll()
        "slip/slip"
    }

    // add/edit (post)
    @RequestMapping(method = RequestMethod.POST)
    String addOrEdit(Slip slip, @RequestParam(required = false) Long customerId) {
        if (userData.exists(customerId)) {
            slip.customer = userData.findOne customerId
            slipData.save slip
        }
        "redirect:/secure/slip"
    }


    // view/display (get)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    String view(Model model, @PathVariable Long id) {
        model.addAllAttributes([slips:slipData.findAll(), slip:slipData.findOne(id)])
        "slip/slip"
    }

    // delete (post)
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    String delete(@PathVariable Long id) {
        slipData.delete id
        "redirect:/secure/slip"
    }
}
