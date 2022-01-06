package com.localfusion.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.localfusion.server.constant.Table;
import com.localfusion.server.entity.Item;
import com.localfusion.server.mapper.ItemMapper;
import com.localfusion.server.service.IItemService;
import com.localfusion.server.service.impl.user.BusinessServiceImpl;
import com.localfusion.server.service.user.IBusinessService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * item service implementation
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements IItemService {

    private IBusinessService businessService;

    public ItemServiceImpl(BusinessServiceImpl businessService) {
        this.businessService = businessService;
    }

    @Override
    public List<Item> get(Integer id, Integer businessId, String name, Double price, String picture, Integer salesVolume,
                          Boolean isDiscounted, Double discount, Boolean isCredited, Integer howManyCredits, Integer howMuchMoney,
                          Boolean special) {
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        if (id != null) {
            queryWrapper = queryWrapper.eq(Table.Item.ID, id);
        }
        if (businessId != null) {
            queryWrapper = queryWrapper.eq(Table.Item.BUSINESS_ID, businessId);
        }
        if (name != null) {
            queryWrapper = queryWrapper.eq(Table.Item.NAME, name);
        }
        if (price != null) {
            queryWrapper = queryWrapper.eq(Table.Item.PRICE, price);
        }
        if (picture != null) {
            queryWrapper = queryWrapper.eq(Table.Item.PICTURE, picture);
        }
        if (salesVolume != null) {
            queryWrapper = queryWrapper.eq(Table.Item.SALES_VOLUME, salesVolume);
        }

        if (isDiscounted != null) {
            queryWrapper = queryWrapper.eq(Table.Item.IS_DISCOUNTED, isDiscounted);
        }
        if (discount != null) {
            queryWrapper = queryWrapper.eq(Table.Item.DISCOUNT, discount);
        }
        if (isCredited != null) {
            queryWrapper = queryWrapper.eq(Table.Item.IS_CREDITED, isCredited);
        }
        if (howManyCredits != null) {
            queryWrapper = queryWrapper.eq(Table.Item.HOW_MANY_CREDITS, howManyCredits);
        }
        if (howMuchMoney != null) {
            queryWrapper = queryWrapper.eq(Table.Item.HOW_MUCH_MONEY, howMuchMoney);
        }
        if (special != null) {
            queryWrapper = queryWrapper.eq(Table.Item.SPECIAL, special);
        }
        return list(queryWrapper);
    }

    /**
     * Add an item by providing it's businessId and name
     * Providing id is not allowed!
     * @param item Item entity
     * @return If successfully added
     */
    @Override
    public boolean add(Item item) {
        if (!businessService.get(item.getBusinessId(), null, null, null).isEmpty()) {
            if (get(null, item.getBusinessId(), item.getName(), null, null, null, null, null, null, null, null, null).isEmpty()) {
                save(item);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * A valid remove request should be (id && ~businessId && ~name || ~id && businessId && name)
     * or will return false.
     * @param id Item id
     * @param businessId Business id
     * @param name Item name
     * @return If it's a valid remove request AND successfully removed
     */
    @Override
    public boolean remove(final Integer id, final Integer businessId, final String name) {
        if (id != null) {
            if(businessId != null || name != null) {
                return false;
            } else if (get(id, null, null, null, null, null, null, null, null, null, null, null).isEmpty()){
                return false;
            } else {
                return remove(new QueryWrapper<Item>().eq(Table.Item.ID, id));
            }
        } else {
            if (businessId == null || name == null) {
                return false;
            } else if (get(null, businessId, name, null, null, null, null, null, null, null, null, null).isEmpty()){
                return false;
            } else {
                return remove(new QueryWrapper<Item>().eq(Table.Item.BUSINESS_ID, businessId).eq(Table.Item.NAME, name));
            }
        }
    }

    /**
     * Update an item using it's id
     * @param item the item entity
     * @return If successfully updated
     */
    @Override
    public boolean update(Item item) {
        if (!businessService.get(item.getBusinessId(), null, null, null).isEmpty()) {
            if (get(item.getId(), null, null, null, null, null, null, null, null, null, null, null).isEmpty()) {
                return false;
            } else {
                updateById(item);
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public int getSalesVolume(Integer businessId, String name) {
        List<Item> itemList = get(null, businessId, name, null, null, null, null, null, null, null, null, null);
        if (itemList.isEmpty()) {
            return -1;
        }
        int salesVolume = 0;
        for (Item item : itemList) {
            salesVolume += item.getSalesVolume();
        }
        return salesVolume;
    }
}
