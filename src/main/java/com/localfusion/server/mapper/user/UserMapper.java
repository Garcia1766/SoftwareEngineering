package com.localfusion.server.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.localfusion.server.entity.user.User;
import org.springframework.stereotype.Repository;

/**
 * user mapper interface
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
