package com.localfusion.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.localfusion.server.constant.Table;
import com.localfusion.server.entity.Rule;
import com.localfusion.server.mapper.RuleMapper;
import com.localfusion.server.service.IRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * rule service implementation
 */
@Service
public class RuleServiceImpl extends ServiceImpl<RuleMapper, Rule> implements IRuleService {

    @Override
    public List<Rule> get(Integer id) {
        QueryWrapper<Rule> ruleQueryWrapper = new QueryWrapper<>();
        if (id != null) {
            ruleQueryWrapper = ruleQueryWrapper.eq(Table.Rule.ID, id);
        }
        return list(ruleQueryWrapper);
    }

    @Override
    public boolean update(Rule rule) {
        if (get(rule.getId()).isEmpty()) {
            return false;
        } else {
            updateById(rule);
            return true;
        }
    }
}
