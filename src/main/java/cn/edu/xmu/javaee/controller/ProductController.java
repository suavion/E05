package cn.edu.xmu.javaee.controller;

import cn.edu.xmu.javaee.controller.dto.ProductDto;
import cn.edu.xmu.javaee.core.exception.BusinessException;
import cn.edu.xmu.javaee.core.model.ReturnObject;
import cn.edu.xmu.javaee.dao.bo.Product;
import cn.edu.xmu.javaee.service.ProductService;
import cn.edu.xmu.javaee.util.CloneFactory;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static cn.edu.xmu.javaee.util.Common.changeHttpStatus;

/**
 * 商品控制器
 * @author Suave
 * @date 2024/11/8
 */
@RestController
@RequestMapping(value = "value = /products", produces = "application/json;charset=UTF-8")
public class ProductController {

    private ProductService productService;
    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductController(ProductService productService) {this.productService = productService;}

    /**
     * 用商品名称搜索商品
     * @param name 商品名称
     * @return
     */
    @GetMapping
    public ReturnObject searchProductByName(String name, HttpServletResponse response) {
        ReturnObject retObj = null;
        try {
            List<Product> productList = null;
            productList = productService.retrieveProductByName(name);
            List<ProductDto> productDtoList = productList.stream().map(o -> CloneFactory.copy(new ProductDto(), o)).collect(Collectors.toList());
            retObj = new ReturnObject(productDtoList);
        } catch (BusinessException e) {
            retObj = new ReturnObject(e.getErrno(), e.getMessage());
        }

        changeHttpStatus(retObj.getCode(), response);
        return retObj;
    }
}
