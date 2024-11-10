package cn.edu.xmu.javaee.dao;

import cn.edu.xmu.javaee.E05Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Suave
 * @date 2024/11/9
 */
@SpringBootTest(classes = E05Application.class)
@Transactional
public class ProductDaoTest {
    private ProductDao productDao;

    @Autowired
    public ProductDaoTest(ProductDao productDao) {this.productDao = productDao;}


}
