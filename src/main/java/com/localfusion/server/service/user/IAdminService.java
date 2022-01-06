package com.localfusion.server.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.localfusion.server.entity.Rule;
import com.localfusion.server.entity.user.Admin;

import java.util.List;

/**
 * admin service
 */
public interface IAdminService extends IService<Admin> {

    List<Admin> get(Integer id, Integer linkage);

    Rule getRule();

    boolean setRequiredCreditsPerLevel(Integer firstToSecond, Integer secondToThird, Integer thirdToFourth, Integer fourthToFifth, Integer fifthToSixth);

    boolean setDiscount(Double discountLevel1, Double discountLevel2, Double discountLevel3, Double discountLevel4, Double discountLevel5, Double discountLevel6);

    boolean setCreditsPer100Yuan(Integer creditsPeer100Yuan);

}
