package com.localfusion.server.controller.user;

import com.localfusion.server.constant.Table;
import com.localfusion.server.constant.URL;
import com.localfusion.server.entity.Rule;
import com.localfusion.server.entity.user.Admin;
import com.localfusion.server.service.impl.user.AdminServiceImpl;
import com.localfusion.server.service.user.IAdminService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class AdminController {

    private IAdminService adminService;

    public AdminController(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }

    @GetMapping(value = URL.AdminController.GET)
    public List<Admin> get(@RequestParam(name = Table.Admin.ID, required = false) Integer id,
                           @RequestParam(name = Table.Admin.LINK, required = false) Integer linkage) {
        return adminService.get(id, linkage);
    }

    @GetMapping(value = URL.AdminController.GET_NUMBER)
    public Integer getNumber() {
        return adminService.get(null, null).size();
    }

    @GetMapping(value = URL.AdminController.GET_RULE)
    public Rule getRule() {
        return adminService.getRule();
    }

    @GetMapping(value = URL.AdminController.SET_REQUIRED_CREDITS_PER_LEVEL)
    public boolean setRequiredCreditsPerLevel(@RequestParam(name = Table.Rule.FIRST_TO_SECOND) Integer firstToSecond,
                                              @RequestParam(name = Table.Rule.SECOND_TO_THIRD) Integer secondToThird,
                                              @RequestParam(name = Table.Rule.THIRD_TO_FOURTH) Integer thirdToFourth,
                                              @RequestParam(name = Table.Rule.FOURTH_TO_FIFTH) Integer fourthToFifth,
                                              @RequestParam(name = Table.Rule.FIFTH_TO_SIXTH) Integer fifthToSixth) {
        return adminService.setRequiredCreditsPerLevel(firstToSecond, secondToThird, thirdToFourth, fourthToFifth, fifthToSixth);
    }

    @GetMapping(value = URL.AdminController.SET_DISCOUNT)
    public boolean setDiscount(@RequestParam(name = Table.Rule.DISCOUNT_LEVEL1) Double discountLevel1,
                               @RequestParam(name = Table.Rule.DISCOUNT_LEVEL2) Double discountLevel2,
                               @RequestParam(name = Table.Rule.DISCOUNT_LEVEL3) Double discountLevel3,
                               @RequestParam(name = Table.Rule.DISCOUNT_LEVEL4) Double discountLevel4,
                               @RequestParam(name = Table.Rule.DISCOUNT_LEVEL5) Double discountLevel5,
                               @RequestParam(name = Table.Rule.DISCOUNT_LEVEL6) Double discountLevel6) {
        return adminService.setDiscount(discountLevel1, discountLevel2, discountLevel3, discountLevel4, discountLevel5, discountLevel6);
    }

    @GetMapping(value = URL.AdminController.SET_CREDITS_PER100_YUAN)
    public boolean setCreditsPer100Yuan(@RequestParam(name = Table.Rule.CREDITS_PER100_YUAN) Integer creditsPeer100Yuan) {
        return adminService.setCreditsPer100Yuan(creditsPeer100Yuan);
    }

}
