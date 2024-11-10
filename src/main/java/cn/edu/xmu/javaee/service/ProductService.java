package cn.edu.xmu.javaee.service;

import cn.edu.xmu.javaee.controller.ProductController;
import cn.edu.xmu.javaee.dao.ProductDao;
import cn.edu.xmu.javaee.dao.bo.Product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author Suave
 * @date 2024/11/8
 */
@Service
public class ProductService {
    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private ProductDao productDao;

    @Autowired
    public ProductService (ProductDao productDao) {this.productDao = productDao;}

    /**
     * 用商品名称搜索商品
     * @param name 商品名称
     * @return 商品对象
     */
    public List<Product> retrieveProductByName(String name) {
        return productDao.retrieveProductByName(name);
    }
}
