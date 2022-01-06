package com.localfusion.server.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.localfusion.server.entity.user.Customer;
import org.springframework.stereotype.Repository;

/**
 * customer mapper interface
 */
@Repository
public interface CustomerMapper extends BaseMapper<Customer> {
}
