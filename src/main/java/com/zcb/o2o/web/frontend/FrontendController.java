package com.zcb.o2o.web.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/frontend")
public class FrontendController {

    /**
     * 首页路由
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    private String index(){
        return "frontend/index";
    }

    /**
     * 商品列表页路由
     *
     * @return
     */
    @RequestMapping(value = "/shoplist", method = RequestMethod.GET)
    private String showShopList() {
        return "frontend/shoplist";
    }
}
