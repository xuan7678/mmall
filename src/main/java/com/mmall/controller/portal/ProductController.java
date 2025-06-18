package com.mmall.controller.portal;


import com.github.pagehelper.PageInfo;
import com.mmall.common.ServiceResponse;
import com.mmall.service.IProductService;
import com.mmall.service.IUserService;
import com.mmall.service.iCategoryService;
import com.mmall.vo.ProductDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping ("/product/")
public class ProductController {

    @Autowired
    private IProductService iProductService;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private iCategoryService iCategoryService;

    @RequestMapping(value = "detail.do")
    @ResponseBody
    public ServiceResponse<ProductDetailVO> detail(Integer productId){
        return iProductService.getProductDetail(productId);
    }


    @RequestMapping(value = "search_list.do")
    @ResponseBody
    public ServiceResponse<PageInfo> searchList(@RequestParam(value = "keyword",required = false)String keyword,
                                                @RequestParam(value = "categoryId",required = false)Integer categoryId,
                                                @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                                @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,
                                                @RequestParam(value = "orderBy",defaultValue = "") String orderBy){
        return iProductService.getProductByKeywordCategory(keyword,categoryId,pageNum,pageSize,orderBy);
    }

}
