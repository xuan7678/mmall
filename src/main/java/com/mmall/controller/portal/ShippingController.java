package com.mmall.controller.portal;


import com.github.pagehelper.PageInfo;
import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServiceResponse;
import com.mmall.pojo.Shipping;
import com.mmall.pojo.User;
import com.mmall.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/shipping/")
public class ShippingController {

    @Autowired
    private IShippingService iShippingService;

    @RequestMapping(value = "add_shipping.do")
    @ResponseBody
    //springMVC可以直接对象绑定Shipping
    public ServiceResponse add(HttpSession session, Shipping shipping) {
        User user = (User) session.getAttribute("user");
        if(user == null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iShippingService.add(user.getId(), shipping);
    }

    @RequestMapping(value = "delete_shipping.do")
    @ResponseBody
    public ServiceResponse delete(HttpSession session, Integer shippingId) {
        User user = (User) session.getAttribute("user");
        if(user == null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iShippingService.delete(user.getId(), shippingId);
    }

    @RequestMapping(value = "update_shippinp.do")
    @ResponseBody
    public ServiceResponse update(HttpSession session, Shipping shipping) {
        User user = (User) session.getAttribute("user");
        if(user == null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iShippingService.update(user.getId(), shipping);
    }

    @RequestMapping(value = "search.do")
    @ResponseBody
    public ServiceResponse search(HttpSession session, Integer shippingId) {
        User user = (User) session.getAttribute("user");
        if(user == null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return  iShippingService.search(user.getId(), shippingId);
    }


    @RequestMapping("list.do")
    @ResponseBody
    public ServiceResponse<PageInfo> list(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
                                         HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user ==null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iShippingService.list(user.getId(),pageNum,pageSize);
    }


}
