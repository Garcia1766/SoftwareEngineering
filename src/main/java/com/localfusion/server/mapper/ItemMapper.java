package com.localfusion.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.localfusion.server.entity.Item;
import org.springframework.stereotype.Repository;

/**
 * item mapper interface
 */
@Repository
public interface ItemMapper extends BaseMapper<Item> {
}
