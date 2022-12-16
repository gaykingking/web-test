package com.ls.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ls.entity.Inventory;
import com.ls.entity.Order;
import com.ls.exception.BusinessException;
import com.ls.exception.DataResult;
import com.ls.exception.ErrorEnum;
import com.ls.mapper.OrderMapper;
import com.ls.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ls.service.InventoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lingsheng
 * @since 2022-10-16
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
    @Resource
    private InventoryService inventoryService;
    @Override
    public Boolean saveOrder(Order order) {
        boolean save;
        try{
            save = save(order);
        }catch (Exception e){
            throw new BusinessException(ErrorEnum.E_20011);
        }
        UpdateWrapper<Inventory> update = Wrappers.update();
        //query.lambda()
        Inventory inventory = new Inventory();
        //inventory.setProductNum();
        inventory.setProductName("测试分布式事务");
        Boolean result = (Boolean) inventoryService.saveInventory(inventory).getData();
        return save&&result;
    }
}
