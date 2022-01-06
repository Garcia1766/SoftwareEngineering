package com.localfusion.server.controller.user;

import com.localfusion.server.constant.Table;
import com.localfusion.server.constant.URL;
import com.localfusion.server.entity.user.Business;
import com.localfusion.server.service.impl.user.BusinessServiceImpl;
import com.localfusion.server.service.user.IBusinessService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class BusinessController {

    private IBusinessService businessService;

    public BusinessController(BusinessServiceImpl businessService) {
        this.businessService = businessService;
    }

    /**
     * Get a business list by providing id, link or comment
     * @param id business id
     * @param linkage the number of users linked
     * @param name the name of business
     * @param comment comments
     * @return A list of business
     */
    @GetMapping(value = URL.BusinessController.GET)
    public List<Business> get(@RequestParam(name = Table.Business.ID, required = false) Integer id,
                              @RequestParam(name = Table.Business.LINK, required = false) Integer linkage,
                              @RequestParam(name = Table.Business.NAME, required = false) String name,
                              @RequestParam(name = Table.Business.COMMENT, required = false) String comment) {
        return businessService.get(id, linkage, name, comment);
    }

    @GetMapping(value = URL.BusinessController.GET_NUMBER)
    public Integer getNumber() {
        return businessService.get(null, null, null, null).size();
    }

    @GetMapping(value = URL.BusinessController.DISTRIBUTE_CREDIT)
    public boolean distributeCredit(@RequestParam(name = "id") Integer id, @RequestParam(name = "delta") Integer delta) {
        return businessService.distributeCredit(id, delta);
    }

    @GetMapping(value = URL.BusinessController.CONSUME_CREDIT)
    public boolean consumeCredit(@RequestParam(name = "id") Integer id, @RequestParam(name = "delta") Integer delta) {
        return businessService.consumeCredit(id, delta);
    }

    @GetMapping(value = URL.BusinessController.INCREASE_TURNOVER)
    public boolean increaseTurnover(@RequestParam(name = "id") Integer id, @RequestParam(name = "money") Integer money) {
        return businessService.increaseTurnover(id, money);
    }

    @GetMapping(value = URL.BusinessController.SETTLE_CREDIT)
    public int settleCredit(@RequestParam(name = "id") Integer id) {
        return businessService.settleCredit(id);
    }

}
