package com.localfusion.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.localfusion.server.entity.Transaction;

import java.util.List;

public interface ITransactionService extends IService<Transaction> {

    List<Transaction> get(final Integer id, final Integer transferor, final String transferorRole, final Integer transferee, final String transfereeRole);

    boolean submit(final Transaction transaction);

}
