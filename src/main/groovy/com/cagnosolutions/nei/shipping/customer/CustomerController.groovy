package com.cagnosolutions.nei.shipping.customer
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
@RequestMapping("/secure/customer")
class CustomerController {

    @Autowired
    CustomerData customerData

    @RequestMapping(method=[RequestMethod.GET])
    String viewAll(Model model) {
        model.addAttribute "customers", customerData.findAll()
        "customer/customer"
    }

    @RequestMapping(method=[RequestMethod.POST])
    String addOrEdit(Customer customer) {
        customerData.save customer
        "redirect:/secure/customer"
    }

    @RequestMapping(value=["/{id}"], method=[RequestMethod.GET])
    String view(@PathVariable Long id, Model model) {
        model.addAllAttributes([customer: customerData.findOne(id), customers: customerData.findAll()])
        "customer/customer"
    }

    @RequestMapping(value=["/{id}"], method=[RequestMethod.POST])
    String delete(@PathVariable Long id) {
        customerData.delete id
        "redirect:/secure/customer"
    }

}
