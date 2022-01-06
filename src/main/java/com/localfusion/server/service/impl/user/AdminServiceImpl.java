package com.localfusion.server.service.impl.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.localfusion.server.constant.Table;
import com.localfusion.server.entity.Rule;
import com.localfusion.server.entity.user.Admin;
import com.localfusion.server.mapper.user.AdminMapper;
import com.localfusion.server.service.IRuleService;
import com.localfusion.server.service.impl.RuleServiceImpl;
import com.localfusion.server.service.user.IAdminService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * admin service
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    private IRuleService ruleService;

    public AdminServiceImpl(RuleServiceImpl ruleService) {
        this.ruleService = ruleService;
    }

    @Override
    public List<Admin> get(Integer id, Integer link) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<Admin>().gt(Table.Admin.LINK, 0);
        if (id != null){
            queryWrapper = queryWrapper.eq(Table.Admin.ID, id);
        }
        if(link != null){
            queryWrapper = queryWrapper.eq(Table.Admin.LINK, link);
        }
        return list(queryWrapper);
    }

    @Override
    public Rule getRule() {
        return ruleService.get(1).get(0);
    }

    @Override
    public boolean setRequiredCreditsPerLevel(Integer firstToSecond, Integer secondToThird, Integer thirdToFourth, Integer fourthToFifth, Integer fifthToSixth) {
        Rule rule = ruleService.get(1).get(0);
        rule.setFirstToSecond(firstToSecond).setSecondToThird(secondToThird).setThirdToFourth(thirdToFourth).setFourthToFifth(fourthToFifth).setFifthToSixth(fifthToSixth);
        return ruleService.update(rule);
    }

    @Override
    public boolean setDiscount(Double discountLevel1, Double discountLevel2, Double discountLevel3, Double discountLevel4, Double discountLevel5, Double discountLevel6) {
        Rule rule = ruleService.get(1).get(0);
        rule.setDiscountLevel1(discountLevel1).setDiscountLevel2(discountLevel2).setDiscountLevel3(discountLevel3).setDiscountLevel4(discountLevel4).setDiscountLevel5(discountLevel5).setDiscountLevel6(discountLevel6);
        return ruleService.update(rule);
    }

    @Override
    public boolean setCreditsPer100Yuan(Integer creditsPeer100Yuan) {
        Rule rule = ruleService.get(1).get(0);
        rule.setCreditsPer100Yuan(creditsPeer100Yuan);
        return ruleService.update(rule);
    }

}
