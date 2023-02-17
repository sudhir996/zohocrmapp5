package com.zohocrm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zohocrm.entities.Contact;
import com.zohocrm.entities.Lead;
import com.zohocrm.service.ContactService;
import com.zohocrm.service.LeadService;

@Controller
public class LeadController {
	
	@Autowired
	private LeadService leadService;
	
	@Autowired
	private ContactService contactService;
	
	@RequestMapping(value ="/createLead" , method = RequestMethod.GET)
	public String viewCreateLeadForm() {          //Handler Method
		return "create_lead";
	}
	
	@RequestMapping(value ="/saveLead", method = RequestMethod.POST)
	public String saveLead(@ModelAttribute("lead") Lead lead, Model model) {
		leadService.saveOneLead(lead);              //save lead with help of object
		model.addAttribute("lead", lead);
		return "lead_info";
		}
	
	@RequestMapping("/convertLead")
	public String convertLead(@RequestParam("id") long id, Model model) {
		Lead lead = leadService.getLeadById(id);
		
		Contact contact = new Contact();                    //take lead and move 
		contact.setFirstName(lead.getFirstName());          //that to contact
		contact.setLastName(lead.getLastName());            //table
		contact.setEmail(lead.getEmail());
		contact.setMobile(lead.getMobile());
		contact.setSource(lead.getSource());
		contactService.saveContact(contact);                //save contact with help of object
		
		leadService.deleteLeadById(id);                     //after saving lead data into contacts, we require to delete 
		List<Contact> contacts = contactService.getContacts();       //lead data from lead table.
		model.addAttribute("contacts", contacts);
		return "list_contacts";
	}
	
	@RequestMapping("/listleads")
	public String listLeads(Model model) {
		List<Lead> leads = leadService.getAllLeads();
		model.addAttribute("leads", leads);
		return "list_leads";
	}
	
	@RequestMapping("/leadInfo")
	public String leadInfo(@RequestParam("id") long id, Model model) {
		Lead lead = leadService.getLeadById(id);
		model.addAttribute("lead", lead);
		return "lead_info";
	}
}