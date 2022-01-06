package com.localfusion.server.service.impl.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.localfusion.server.constant.Table;
import com.localfusion.server.entity.user.Customer;
import com.localfusion.server.mapper.user.CustomerMapper;
import com.localfusion.server.service.IRuleService;
import com.localfusion.server.service.impl.RuleServiceImpl;
import com.localfusion.server.service.user.ICustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * customer service
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

    private IRuleService ruleService;

    public CustomerServiceImpl(RuleServiceImpl ruleService) {
        this.ruleService = ruleService;
    }

    @Override
    public List<Customer> get(final Integer id, final Integer link, final String comment) {
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<Customer>().gt(Table.Customer.LINK, 0);
        if (id != null) {
            queryWrapper = queryWrapper.eq(Table.Customer.ID, id);
        }
        if (link != null) {
            queryWrapper = queryWrapper.eq(Table.Customer.LINK, link);
        }
        if (comment != null) {
            queryWrapper = queryWrapper.in(Table.Customer.COMMENT, comment);
        }
        return list(queryWrapper);
    }

    @Override
    public boolean update(final int id, final Double creditDelta, final Double balanceDelta, final Double expDelta, final String comment) {
        if (get(id, null, null).isEmpty()) {
            return false;
        } else {
            Customer customer = getById(id);
            double credit = customer.getCredit() + (creditDelta == null ? 0 : creditDelta);
            customer.setCredit(credit);
            customer.setBalance(customer.getBalance() + (balanceDelta == null ? 0 : balanceDelta));
            customer.setExp(customer.getExp() + (expDelta == null ? 0 : expDelta));
            int level;
            if (customer.getExp() < ruleService.get(1).get(0).getFirstToSecond()) {
                level = 1;
            } else if (customer.getExp() < ruleService.get(1).get(0).getSecondToThird()) {
                level = 2;
            } else if (customer.getExp() < ruleService.get(1).get(0).getThirdToFourth()) {
                level = 3;
            } else if (customer.getExp() < ruleService.get(1).get(0).getFourthToFifth()) {
                level = 4;
            } else if (customer.getExp() < ruleService.get(1).get(0).getFifthToSixth()) {
                level = 5;
            } else {
                level = 6;
            }
            customer.setLevel(level);
            customer.setComment(comment);
            return updateById(customer);
        }
    }

}
