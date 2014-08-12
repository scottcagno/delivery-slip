package com.cagnosolutions.nei.shipping.customer

import groovy.transform.CompileStatic
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
@CompileStatic
@RequestMapping("/secure/customer")
class CustomerController {

	@Autowired
	CustomerService customerService

	@RequestMapping(method=[RequestMethod.GET])
	String viewAll(Model model, @RequestParam(required = false) Integer page, @RequestParam(required = false) String sort) {
		def customers = customerService.findAll(page? page-1 :0 , 20, sort?:"id")
		page = (page? page :1)
		def ub = (((customers.totalPages - page) >= 4)? page + 4 : customers.totalPages)
		if (page < 6) {
			ub = ((customers.totalPages > 10)? 10 : customers.totalPages)
		}
		def lb = (((ub - 9) > 0)? ub-9: 1)
		model.addAllAttributes([customers: customers, lb: lb, ub : ub])
		"customer/customer"
	}

	@RequestMapping(method=[RequestMethod.POST])
	String addOrEdit(Customer customer) {
		customerService.save customer
		"redirect:/secure/customer"
	}

	@RequestMapping(value=["/{id}"], method=[RequestMethod.GET])
	String view(@PathVariable Long id, Model model) {
		model.addAllAttributes([customer: customerService.findOne(id), customers: customerService.findAll()])
		"customer/customer"
	}

	@RequestMapping(value=["/{id}"], method=[RequestMethod.POST])
	String delete(@PathVariable Long id) {
		customerService.delete id
		"redirect:/secure/customer"
	}

}
