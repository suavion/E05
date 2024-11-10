package cn.edu.xmu.javaee.controller;

import cn.edu.xmu.javaee.E05Application;
import cn.edu.xmu.javaee.core.model.ReturnNo;
import cn.edu.xmu.javaee.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Suave
 * @date 2024/11/10
 */
@SpringBootTest(classes = E05Application.class)
@AutoConfigureMockMvc
@Transactional
public class ProductControllerTest {
    private ProductService productService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    public ProductControllerTest(ProductService productService) {this.productService = productService;}

    private final String Get_PRODUCT_URL = "/products";
    private final String PRODUCT_NAME = "欢乐家岭南杂果罐头";

    @Test
    public void searchProductByName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(Get_PRODUCT_URL).param("name", PRODUCT_NAME))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno").value(ReturnNo.OK.getErrNo()));
    }
}
