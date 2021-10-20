//package com.laptrinhjavaweb.controller.admin;
//
//import com.laptrinhjavaweb.service.impl.TransactionService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//
//@Controller(value = "transactionOfStaff")
//public class TransactionController {
//    @Autowired
//    private TransactionService transactionService;
//    @RequestMapping(value = "/admin/transaction-{id}", method = RequestMethod.GET)
//    public ModelAndView buildingList(@PathVariable(value = "id") Long customerId ) {
//        ModelAndView mav = new ModelAndView("admin/transaction/transaction");
//        mav.addObject("getcustomerId",customerId);
//        mav.addObject("transactionTypes",transactionService.getTransaction() );
//        mav.addObject("transactions",transactionService.getTransactionByCustomer(customerId));
//        return mav;
//    }
//}
