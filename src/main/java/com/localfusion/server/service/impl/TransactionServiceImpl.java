package com.localfusion.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.localfusion.server.constant.Table;
import com.localfusion.server.constant.UserRole;
import com.localfusion.server.entity.Item;
import com.localfusion.server.entity.Transaction;
import com.localfusion.server.mapper.TransactionMapper;
import com.localfusion.server.service.IItemService;
import com.localfusion.server.service.ITransactionService;
import com.localfusion.server.service.impl.user.BusinessServiceImpl;
import com.localfusion.server.service.impl.user.CustomerServiceImpl;
import com.localfusion.server.service.user.IBusinessService;
import com.localfusion.server.service.user.ICustomerService;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * transaction service implementation
 */
@Service
public class TransactionServiceImpl extends ServiceImpl<TransactionMapper, Transaction> implements ITransactionService {

    private IBusinessService businessService;
    private ICustomerService customerService;
    private IItemService itemService;

    public TransactionServiceImpl(final BusinessServiceImpl businessService, final CustomerServiceImpl customerService, final ItemServiceImpl itemService) {
        this.businessService = businessService;
        this.customerService = customerService;
        this.itemService = itemService;
    }

    @Override
    public List<Transaction> get(final Integer id, final Integer transferor, final String transferorRole, final Integer transferee, final String transfereeRole) {
        QueryWrapper<Transaction> transactionQueryWrapper = new QueryWrapper<>();
        if (id != null) {
            transactionQueryWrapper = transactionQueryWrapper.eq(Table.Transaction.ID, id);
        }
        if (transferor != null) {
            transactionQueryWrapper = transactionQueryWrapper.eq(Table.Transaction.TRANSFEROR, transferor);
        }
        if (transferorRole != null) {
            transactionQueryWrapper = transactionQueryWrapper.eq(Table.Transaction.TRANSFEROR_ROLE, transferorRole);
        }
        if (transferee != null) {
            transactionQueryWrapper = transactionQueryWrapper.eq(Table.Transaction.TRANSFEREE, transferee);
        }
        if (transfereeRole != null) {
            transactionQueryWrapper = transactionQueryWrapper.eq(Table.Transaction.TRANSFEREE_ROLE, transfereeRole);
        }
        return list(transactionQueryWrapper);
    }

    /**
     * check if the input participant is legal
     *
     * @param id   participant id
     * @param role participant role
     * @return if the participant is legal
     */
    private boolean checkParticipant(final int id, final String role) {
        if (role.equals(UserRole.BUSINESS)) {
            return !businessService.get(id, null, null, null).isEmpty();
        } else if (role.equals(UserRole.CUSTOMER)) {
            return !customerService.get(id, null, null).isEmpty();
        } else {
            return false;
        }
    }

    /**
     * check if the input transaction is legal
     *
     * @param transaction input transaction
     * @return if the transaction is legal
     */
    private boolean checkTransaction(final Transaction transaction) {
        return getById(transaction.getId()) == null
                && checkParticipant(transaction.getTransferor(), transaction.getTransferorRole())
                && checkParticipant(transaction.getTransferee(), transaction.getTransfereeRole());
    }

    /**
     * Update transferor's credit
     *
     * @param id    transferor id
     * @param role  transferor role
     * @param credit value of credit
     */
    private void submitTransferorDelta(final int id, final String role, final double credit, final double money, final double exp) {
        if (role.equals(UserRole.BUSINESS)) {  // If transferor is business, he distributes credit
            businessService.distributeCredit(id, (int) -credit);
            businessService.increaseTurnover(id, (int) -money);
        } else { // UserRole.Customer
            customerService.update(id, credit, money, exp, null);
        }
    }

    /**
     * Update transferee's credit
     *
     * @param id    transferee id
     * @param role  transferee role
     * @param value value of credit
     */
    private void submitTransfereeDelta(final int id, final String role, final double value, final double money, final double exp) {
        if (role.equals(UserRole.BUSINESS)) {  // If transferee is business, he consumes credit
            businessService.consumeCredit(id, (int) value);
            businessService.increaseTurnover(id, (int) money);
        } else { // UserRole.Customer
            customerService.update(id, value, money, exp, null);
        }
    }

    /**
     * Submit transaction. First check if all is legal, then call submitDelta
     *
     * @param transaction transaction
     * @return If successfully submit and save a transaction.
     */
    @Override
    public boolean submit(final Transaction transaction) {
        if (checkTransaction(transaction)) {
            submitTransferorDelta(transaction.getTransferor(), transaction.getTransferorRole(), -transaction.getCredit(), -transaction.getMoney(), transaction.getExp());
            submitTransfereeDelta(transaction.getTransferee(), transaction.getTransfereeRole(), +transaction.getCredit(), +transaction.getMoney(), transaction.getExp());
            for (String itemId : transaction.getItemIdList().split(",")) {
                if (!itemId.equals("")) {
                    Item item = itemService.getById(Integer.parseInt(itemId));
                    item.setSalesVolume(item.getSalesVolume() + 1);
                    itemService.updateById(item);
                }
            }
            return save(transaction.setTime(Calendar.getInstance(Locale.CHINA).getTime()));
        } else {
            return false;
        }
    }

}
