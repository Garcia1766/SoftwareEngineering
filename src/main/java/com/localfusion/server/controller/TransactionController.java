package com.localfusion.server.controller;

import com.localfusion.server.constant.URL;
import com.localfusion.server.entity.Transaction;
import com.localfusion.server.service.impl.TransactionServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * transaction controller
 */
@RestController
@CrossOrigin(origins = "*")
public class TransactionController {

    private TransactionServiceImpl transactionService;

    public TransactionController(final TransactionServiceImpl transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping(value = URL.TransactionController.GET)
    public List<Transaction> get(@RequestParam(name = "id", required = false) final Integer id,
                                 @RequestParam(name = "transferor", required = false) final Integer transferor,
                                 @RequestParam(name = "transferorRole", required = false) final String transferorRole,
                                 @RequestParam(name = "transferee", required = false) final Integer transferee,
                                 @RequestParam(name = "transfereeRole", required = false) final String transfereeRole) {
        return transactionService.get(id, transferor, transferorRole, transferee, transfereeRole);
    }

    @PostMapping(value = URL.TransactionController.SUBMIT)
    public boolean submit(@RequestBody final Transaction transaction) {
        return transactionService.submit(transaction);
    }

}
