package com.localfusion.server.service.impl.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.localfusion.server.constant.Table;
import com.localfusion.server.entity.user.Business;
import com.localfusion.server.mapper.user.BusinessMapper;
import com.localfusion.server.service.IRuleService;
import com.localfusion.server.service.impl.RuleServiceImpl;
import com.localfusion.server.service.user.IBusinessService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * business service
 */
@Service
public class BusinessServiceImpl extends ServiceImpl<BusinessMapper, Business> implements IBusinessService {

    private IRuleService ruleService;

    public BusinessServiceImpl(RuleServiceImpl ruleService) {
        this.ruleService = ruleService;
    }

    @Override
    public List<Business> get(Integer id, Integer link, String name, String comment) {
        QueryWrapper<Business> queryWrapper = new QueryWrapper<Business>().gt(Table.Business.LINK, 0);
        if (id != null) {
            queryWrapper = queryWrapper.eq(Table.Business.ID, id);
        }
        if (link != null) {
            queryWrapper = queryWrapper.eq(Table.Business.LINK, link);
        }
        if (name != null) {
            queryWrapper = queryWrapper.eq(Table.Business.NAME, name);
        }
        if (comment != null) {
            queryWrapper = queryWrapper.in(Table.Business.COMMENT, comment);
        }
        return list(queryWrapper);
    }

    /**
     * A business distributes an amount of credits to a customer
     * @param id  Business id
     * @param delta  The amount of credits
     * @return  If successfully distributed
     */
    @Override
    public boolean distributeCredit(int id, int delta) {
        if (get(id, null, null, null).isEmpty()) {
            return false;
        } else {
            Business business = getById(id);
            int credit = business.getDistributedCredit() + delta;
            business.setDistributedCredit(credit);
            return updateById(business);
        }
    }

    /**
     * A business consumes an amount of credits from a customer
     * @param id  Business id
     * @param delta  The amount of credits
     * @return  If successfully consumed
     */
    @Override
    public boolean consumeCredit(int id, int delta) {
        if (get(id, null, null, null).isEmpty()) {
            return false;
        } else {
            Business business = getById(id);
            int credit = business.getConsumedCredit() + delta;
            business.setConsumedCredit(credit);
            return updateById(business);
        }
    }

    /**
     * A business sell items, its turnover increases
     * @param id business id
     * @param money money
     * @return If successfully increased
     */
    @Override
    public boolean increaseTurnover(int id, int money) {
        if (get(id, null, null, null).isEmpty()) {
            return false;
        } else {
            Business business = getById(id);
            business.setTurnover(business.getTurnover() + money);
            return updateById(business);
        }
    }

    /**
     * Settle business credits
     * @param id business id
     * @return The money got through settling credits; -1 if business doesn't exist
     */
    @Override
    public int settleCredit(int id) {
        if (get(id, null, null, null).isEmpty()) {
            return -1;
        } else {
            Business business = getById(id);
            int answer = business.getConsumedCredit() - business.getDistributedCredit() < 0 ? 0 :
                    (business.getConsumedCredit() - business.getDistributedCredit()) / ruleService.get(1).get(0).getCreditsPer100Yuan() * 100;
            business.setConsumedCredit(0).setDistributedCredit(0).setTurnover(business.getTurnover() + answer);
            updateById(business);
            return answer;
        }
    }

}
