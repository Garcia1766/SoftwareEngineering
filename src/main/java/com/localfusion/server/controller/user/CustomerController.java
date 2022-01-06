package com.localfusion.server.controller.user;

import com.localfusion.server.constant.Table;
import com.localfusion.server.constant.URL;
import com.localfusion.server.entity.user.Customer;
import com.localfusion.server.service.impl.user.CustomerServiceImpl;
import com.localfusion.server.service.user.ICustomerService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class CustomerController {

    private ICustomerService customerService;

    public CustomerController(final CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = URL.CustomerController.GET)
    public List<Customer> get(@RequestParam(name = Table.Customer.ID, required = false) final Integer id,
                              @RequestParam(name = Table.Customer.LINK, required = false) final Integer linkage,
                              @RequestParam(name = Table.Customer.COMMENT, required = false) final String comment) {
        return customerService.get(id, linkage, comment);
    }

    @GetMapping(value = URL.CustomerController.GET_NUMBER)
    public Integer getNumber() {
        return customerService.get(null, null, null).size();
    }

    @GetMapping(value = URL.CustomerController.UPDATE)
    public boolean update(@RequestParam(name = Table.Customer.ID) final int id,
                          @RequestParam(name = "creditDelta", required = false) final Double creditDelta,
                          @RequestParam(name = "balanceDelta", required = false) final Double balanceDelta,
                          @RequestParam(name = "expDelta", required = false) final Double expDelta,
                          @RequestParam(name = "comment", required = false) final String comment) {
        return customerService.update(id, creditDelta, balanceDelta, expDelta, comment);
    }

}
