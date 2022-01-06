package com.localfusion.server.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.localfusion.server.entity.user.Business;

import java.util.List;

/**
 * business service
 */
public interface IBusinessService extends IService<Business> {

    List<Business> get(Integer id, Integer linkage, String name, String comment);

    boolean distributeCredit(int id, int delta);

    boolean consumeCredit(int id, int delta);

    boolean increaseTurnover(int id, int money);

    int settleCredit(int id);

}
