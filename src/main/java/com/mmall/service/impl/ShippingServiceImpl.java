package com.mmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.mmall.common.ServiceResponse;
import com.mmall.dao.ProductMapper;
import com.mmall.dao.ShippingMapper;
import com.mmall.pojo.Shipping;
import com.mmall.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("iShippingService")
public class ShippingServiceImpl implements IShippingService {

    @Autowired
    private IShippingService iShippingService;

    @Autowired
    private ShippingMapper shippingMapper;


    public ServiceResponse add(Integer userId, Shipping shipping){
        shipping.setUserId(userId);
        int row = shippingMapper.insert(shipping);
        if(row > 0){
            Map result = Maps.newHashMap();
            result.put("shippingId",shipping.getId());
            return ServiceResponse.createBySuccess("新建地址成功",result);
        }
        return ServiceResponse.createByErrorMessage("新建地址失败");

    }

    public ServiceResponse<String> delete(Integer userId, Integer shippingId){
        int row = shippingMapper.deleteByShippingIdUserId(userId,shippingId);
        if(row > 0){
            return ServiceResponse.createBySuccessMessage("删除地址成功");
        }
        return ServiceResponse.createByErrorMessage("<删除地址失败>");
    }


    public ServiceResponse<String> update(Integer userId, Shipping shipping){
        shipping.setUserId(userId);
        int row = shippingMapper.updateByShipping(shipping);
        if(row > 0){
            return ServiceResponse.createBySuccessMessage("更新地址成功");
        }
        return ServiceResponse.createByErrorMessage("更新地址失败");
    }

    public ServiceResponse<Shipping> search(Integer userId, Integer shippingId){
        Shipping shipping = shippingMapper.selectByShippingIdUserId(userId,shippingId);
        if(shipping == null){
            return ServiceResponse.createByErrorMessage("无法查询到该地址");
        }
        return ServiceResponse.createBySuccess("更新地址成功",shipping);
    }

    public ServiceResponse<PageInfo> list(Integer userId, int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Shipping> shippingList = shippingMapper.selectByUserId(userId);
        PageInfo pageInfo = new PageInfo(shippingList);
        return ServiceResponse.createBySuccess(pageInfo);
    }








}
