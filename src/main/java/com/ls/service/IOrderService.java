package com.ls.service;

import com.ls.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lingsheng
 * @since 2022-10-16
 */
public interface IOrderService extends IService<Order> {

    Boolean saveOrder(Order order);
}
