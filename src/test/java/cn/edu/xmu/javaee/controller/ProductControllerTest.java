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
    private final String PRODUCT_NAME_SUCCESS = "欢乐家岭南杂果罐头";
    private final String PRODUCT_NAME_FAIL = "不存在商品";

    @Test
    public void searchProductByName1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(Get_PRODUCT_URL).param("name", PRODUCT_NAME_SUCCESS))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno").value(ReturnNo.OK.getErrNo()));
    }

    @Test
    public void searchProductByName2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(Get_PRODUCT_URL).param("name", PRODUCT_NAME_FAIL))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno").value(ReturnNo.INTERNAL_SERVER_ERR.getErrNo()));
    }
}
