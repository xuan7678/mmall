package com.mmall.service;

import com.mmall.common.ServiceResponse;
import com.mmall.pojo.Category;

import java.util.List;

public interface iCategoryService {

    ServiceResponse addCategory(String categoryName, Integer parentId);
    ServiceResponse updateCategoryName(Integer categoryId,String categoryName);
    ServiceResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);
    ServiceResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId);
}
