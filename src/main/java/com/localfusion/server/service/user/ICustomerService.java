package com.localfusion.server.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.localfusion.server.entity.user.Customer;

import java.util.List;

/**
 * customer service
 */
public interface ICustomerService extends IService<Customer> {

    List<Customer> get(final Integer id, final Integer link, final String comment);

    boolean update(final int id, final Double creditDelta, final Double balanceDelta, final Double expDelta, final String comment);

}
