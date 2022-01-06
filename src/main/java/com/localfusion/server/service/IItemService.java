package com.localfusion.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.localfusion.server.entity.Item;

import java.util.List;

/**
 * item service interface
 */
public interface IItemService extends IService<Item> {

    List<Item> get(Integer id, Integer businessId, String name, Double price, String picture, Integer salesVolume,
    Boolean isDiscounted, Double discount, Boolean isCredited, Integer howManyCredits, Integer howMuchMoney,
                   Boolean special);

    boolean add(final Item item);

    boolean remove(final Integer id, final Integer businessId, final String name);

    boolean update(final Item item);

    /**
     * Sales volume of an item
     * @param businessId business id
     * @param name item name
     * @return -1 if the item doesn't exist
     */
    int getSalesVolume(final Integer businessId, final String name);

}
