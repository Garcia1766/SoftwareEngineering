package com.localfusion.server.controller;

import com.localfusion.server.constant.Table;
import com.localfusion.server.constant.URL;
import com.localfusion.server.entity.Item;
import com.localfusion.server.service.impl.ItemServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * item controller
 */
@RestController
@CrossOrigin(origins = "*")
public class ItemController {

    private ItemServiceImpl itemService;

    public ItemController(ItemServiceImpl itemService) {
        this.itemService = itemService;
    }

    /**
     * Get a list of items by providing any ones among below 10 features
     * @param id item id
     * @param businessId business id
     * @param name item name
     * @param price price, Double
     * @param picture picture resources, String
     * @param isDiscounted if it's discounted
     * @param discount 0 < discount < 1
     * @param isCredited if credits can be used to buy this item
     * @param howManyCredits how many credits can replace a certain amount of money
     * @param howMuchMoney how much money is these credits equal to
     * @return The list of the items
     */
    @GetMapping(value = URL.ItemController.GET)
    public List<Item> get(@RequestParam(name = Table.Item.ID, required = false) Integer id,
                          @RequestParam(name = Table.Item.BUSINESS_ID, required = false) Integer businessId,
                          @RequestParam(name = Table.Item.NAME, required = false) String name,
                          @RequestParam(name = Table.Item.PRICE, required = false) Double price,
                          @RequestParam(name = Table.Item.PICTURE, required = false) String picture,
                          @RequestParam(name = Table.Item.SALES_VOLUME, required = false) Integer salesVolume,
                          @RequestParam(name = Table.Item.IS_DISCOUNTED, required = false) Boolean isDiscounted,
                          @RequestParam(name = Table.Item.DISCOUNT, required = false) Double discount,
                          @RequestParam(name = Table.Item.IS_CREDITED, required = false) Boolean isCredited,
                          @RequestParam(name = Table.Item.HOW_MANY_CREDITS, required = false) Integer howManyCredits,
                          @RequestParam(name = Table.Item.HOW_MUCH_MONEY, required = false) Integer howMuchMoney,
                          @RequestParam(name = Table.Item.SPECIAL, required = false) Boolean special) {
        return itemService.get(id, businessId, name, price, picture, salesVolume, isDiscounted, discount, isCredited, howManyCredits, howMuchMoney, special);
    }

    @PostMapping(value = URL.ItemController.ADD)
    public boolean add(@RequestBody Item item) {
        return itemService.add(item);
    }

    /**
     * A valid remove request should provide only id OR only businessId and name
     * (id && ~businessId && ~name || ~id && businessId && name)
     * Otherwise the request will be regarded as invalid
     * @param id item id
     * @param businessId business id
     * @param name item name
     * @return If it's a valid request AND successfully removed
     */
    @GetMapping(value = URL.ItemController.REMOVE)
    public boolean remove(@RequestParam(name = Table.Item.ID, required = false) Integer id,
                          @RequestParam(name = Table.Item.BUSINESS_ID, required = false) Integer businessId,
                          @RequestParam(name = Table.Item.NAME, required = false) String name) {
        return itemService.remove(id, businessId, name);
    }

    @PostMapping(value = URL.ItemController.UPDATE)
    public boolean update(@RequestBody Item item) {
        return itemService.update(item);
    }

    @GetMapping(value = URL.ItemController.GET_SALES_VOLUME)
    public int getSalesVolume(@RequestParam(name = Table.Item.BUSINESS_ID, required = false) Integer businessId,
                              @RequestParam(name = Table.Item.NAME, required = false) String name) {
        return itemService.getSalesVolume(businessId, name);
    }
}
