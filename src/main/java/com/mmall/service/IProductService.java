package com.mmall.service;
import com.github.pagehelper.PageInfo;
import com.mmall.common.ServiceResponse;
import com.mmall.pojo.Product;
import com.mmall.vo.ProductDetailVO;


public interface IProductService {

    ServiceResponse saveOrUpdateProduct(Product product);
    ServiceResponse<String> setSaleStatus(Integer productId, Integer saleStatus);
    ServiceResponse<Object> manageProductDetail(Integer productId);
    ServiceResponse<PageInfo> getProductList(int pageNum, int pageSize);
    ServiceResponse<PageInfo> searchProduct(String productName,Integer productId,int pageNum,int pageSize);
    ServiceResponse<ProductDetailVO> getProductDetail(Integer productId);
    ServiceResponse<PageInfo> getProductByKeywordCategory(String keyword, Integer categoryId, int pageNum, int pageSize, String orderBy);







}
