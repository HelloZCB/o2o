package com.zcb.o2o.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zcb.o2o.dto.ImageHolder;
import com.zcb.o2o.dto.ProductExecution;
import com.zcb.o2o.entity.Product;
import com.zcb.o2o.entity.Shop;
import com.zcb.o2o.enums.ProductStateEnum;
import com.zcb.o2o.service.ProductCategoryService;
import com.zcb.o2o.service.ProductService;
import com.zcb.o2o.util.CodeUtil;
import com.zcb.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shopadmin")
public class ProductManagementController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;

    private static final int IMAGEMAXCOUNT = 6;

    @RequestMapping(value = "/addproduct", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> addProduct(HttpServletRequest request) {
        Map<String, Object> modleMap = new HashMap<>();
        // 验证码校验
        if(!CodeUtil.checkVerifyCode(request)){
            modleMap.put("success", false);
            modleMap.put("errMsg", "验证码错误");
            return modleMap;
        }
        // 接收前端参数的变量的初始化，包括商品，缩略图，详情图列表实体类
        ObjectMapper mapper = new ObjectMapper();
        Product product = null;
        String productStr = HttpServletRequestUtil.getString(request, "productStr");
        MultipartHttpServletRequest multipartHttpServletRequest = null;
        ImageHolder thumbnail = null;
        List<ImageHolder> productImgList = new ArrayList<>();
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        try {
            if (multipartResolver.isMultipart(request)){
                multipartHttpServletRequest = (MultipartHttpServletRequest)request;
                CommonsMultipartFile thumbnailFile = (CommonsMultipartFile) multipartHttpServletRequest.getFile("thumbnail");
                thumbnail = new ImageHolder(thumbnailFile.getOriginalFilename(), thumbnailFile.getInputStream());
                for (int i = 0; i < IMAGEMAXCOUNT; i++){
                    CommonsMultipartFile productImgFile = (CommonsMultipartFile)multipartHttpServletRequest.getFile("productImg" + i);
                    if (productImgFile != null){
                        ImageHolder productImg = new ImageHolder(productImgFile.getOriginalFilename(), productImgFile.getInputStream());
                        productImgList.add(productImg);
                    } else {
                        break;
                    }
                }
            } else {
                modleMap.put("success", false);
                modleMap.put("errMsg", "pictrue is null");
                return modleMap;
            }
        } catch (Exception e){
            modleMap.put("success", false);
            modleMap.put("errMsg", e.toString());
            return modleMap;
        }

        try {
            product = mapper.readValue(productStr, Product.class);
        } catch (Exception e){
            modleMap.put("success", false);
            modleMap.put("errMsg", e.toString());
            return modleMap;
        }

        if (product != null && thumbnail != null && productImgList.size() > 0){
            try {
                Shop currentShop = (Shop)request.getSession().getAttribute("currentShop");
                product.setShop(currentShop);

                ProductExecution productExecution = productService.addProduct(product, thumbnail, productImgList);
                if (productExecution.getState() == ProductStateEnum.SUCCESS.getState()){
                    modleMap.put("success", true);
                } else {
                    modleMap.put("success", false);
                    modleMap.put("errMsg", productExecution.getStateInfo());
                }
            }catch (RuntimeException e){
                modleMap.put("success", false);
                modleMap.put("errMsg", e.toString());
                return modleMap;
            }
        } else {
            modleMap.put("success", false);
            modleMap.put("errMsg", "please input product information");
        }

        return modleMap;
    }
}
