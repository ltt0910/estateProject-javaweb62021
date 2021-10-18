package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.service.impl.BuildingService;
import com.laptrinhjavaweb.service.impl.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "customerOfAdmin")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    BuildingService buildingService;
    @RequestMapping(value = "/admin/customer-list", method = RequestMethod.GET)
    public ModelAndView buildingList(@ModelAttribute("modelSearch") CustomerDTO customerDTO) {
        ModelAndView mav = new ModelAndView("admin/customer/list");
        mav.addObject("modelSearch",customerDTO);
        mav.addObject("customers",customerService.searchCustomer(customerDTO));
        mav.addObject("staffMaps",customerService.getStaff(customerDTO.getId()));

        return mav;
    }
    @RequestMapping(value = "/admin/customer-edit", method = RequestMethod.GET)
        public ModelAndView customerEdit(@ModelAttribute("addCustomer") CustomerDTO customerDTO) {
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        mav.addObject("staffMaps",buildingService.getStaff(customerDTO.getId()));
        mav.addObject("addCustomer",customerDTO);
        return mav;
    }
    @RequestMapping(value = "/admin/customer-edit-{id}", method = RequestMethod.GET)
    public ModelAndView customerEdit(@PathVariable(value = "id") Long id ) {
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        CustomerDTO customerDTO =  customerService.findOne(id);
        mav.addObject("addCustomer",customerDTO);
        return mav;
    }
}
