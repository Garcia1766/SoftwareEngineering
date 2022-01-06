package com.localfusion.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.localfusion.server.entity.Transaction;
import org.springframework.stereotype.Repository;

/**
 * transaction mapper interface
 */
@Repository
public interface TransactionMapper extends BaseMapper<Transaction> {
}
