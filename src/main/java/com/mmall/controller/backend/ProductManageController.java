package com.mmall.controller.backend;


import com.google.common.collect.Maps;
import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServiceResponse;
import com.mmall.pojo.Product;
import com.mmall.pojo.User;
import com.mmall.service.IProductService;
import com.mmall.service.IUserService;
import com.mmall.service.iCategoryService;
import com.mmall.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/manage/product")
public class ProductManageController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IProductService iProductService;

    @RequestMapping("save.do")
    @ResponseBody
    public ServiceResponse productSave(HttpSession session, Product product){
        User user =  (User)session.getAttribute(Const.CURRENT_USER);
        if(user==null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录管理员");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //填充增加产品的业务逻辑
            return  iProductService.saveOrUpdateProduct(product);
        }else{
            return ServiceResponse.createByErrorMessage("不是管理员，无权操作");
        }
    }

    @RequestMapping("set_sale_status.do")
    @ResponseBody
    public ServiceResponse setSaleStatus(HttpSession session, Integer productId, Integer saleStatus){
        User user =  (User)session.getAttribute(Const.CURRENT_USER);
        if(user==null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录管理员");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //更新产品销售状态
            return iProductService.setSaleStatus(productId, saleStatus);

        }else{
            return ServiceResponse.createByErrorMessage("不是管理员，无权操作");
        }
    }


    @RequestMapping("detail.do")
    @ResponseBody
    public ServiceResponse getDetail(HttpSession session, Integer productId){
        User user =  (User)session.getAttribute(Const.CURRENT_USER);
        if(user==null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录管理员");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //填充业务
            return iProductService.manageProductDetail(productId);
        }else{
            return ServiceResponse.createByErrorMessage("不是管理员，无权操作");
        }
    }

    @RequestMapping("list.do")
    @ResponseBody
    public ServiceResponse getList(HttpSession session, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");

        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //填充业务
            return iProductService.getProductList(pageNum,pageSize);
        }else{
            return ServiceResponse.createByErrorMessage("无权限操作");
        }
    }

    @RequestMapping("search.do")
    @ResponseBody
    public ServiceResponse productSearch(HttpSession session,String productName,Integer productId, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,@RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //填充业务
            return iProductService.searchProduct(productName,productId,pageNum,pageSize);
        }else{
            return ServiceResponse.createByErrorMessage("无权限操作");
        }
    }

    @RequestMapping("upload.do")
    @ResponseBody
    public ServiceResponse upload(HttpSession session, @RequestParam(value = "upload_file",required = false) MultipartFile file, HttpServletRequest request){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            String path = request.getSession().getServletContext().getRealPath("upload");
            String targetFileName = iFileService.upload(file,path);
            String url = PropertiesUtil.getProperty("ftp.server.http.prefix")+targetFileName;

            Map fileMap = Maps.newHashMap();
            fileMap.put("uri",targetFileName);
            fileMap.put("url",url);
            return ServiceResponse.createBySuccess(fileMap);
        }else{
            return ServiceResponse.createByErrorMessage("无权限操作");
        }
    }












}
