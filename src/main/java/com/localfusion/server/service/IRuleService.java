package com.localfusion.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.localfusion.server.entity.Rule;

import java.util.List;

/**
 * rule service interface
 */
public interface IRuleService extends IService<Rule> {
    List<Rule> get(Integer id);

    boolean update(final Rule rule);
}
