package com.cow.horse.service.impl;

import com.cow.horse.service.OrderAddressService;
import com.cow.horse.dao.OrderAddressDao;
import com.cow.horse.model.OrderAddressModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderAddressServiceImpl implements OrderAddressService {

    @Resource
    private OrderAddressDao orderAddressDao;

    /**
     * 为订单新增地址信息
     * @param orderAddressModel
     * @return
     */
    public boolean addOrderAddress(OrderAddressModel orderAddressModel){
        return orderAddressDao.insert(orderAddressModel)==1;
    }

    /**
     * 更新订单的地址信息，未验证用户身份
     * @param orderAddressModel
     * @return
     */
    public boolean updateOrderAddress(OrderAddressModel orderAddressModel){
        orderAddressModel.setOrderId(null);
        return orderAddressDao.updateByPrimaryKeySelective(orderAddressModel)==1;
    }

    /**
     * 获取订单的地址信息
     * orderId建索引
     * @param orderId
     * @return
     */
    public OrderAddressModel getOrderAddress(Long orderId){
        return orderAddressDao.selectByOrderId(orderId);
    }
}
