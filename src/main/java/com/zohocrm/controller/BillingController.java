package com.zohocrm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zohocrm.entities.Billing;
import com.zohocrm.service.BillingService;
@Controller
public class BillingController {
	@Autowired
	private BillingService billingService;
	
	@RequestMapping("/listbillings")
	public String listBillings(Model model) {
		List<Billing> billings = billingService.getAllBillings();
		model.addAttribute("billings", billings);
		return "list_bills";
	}
}