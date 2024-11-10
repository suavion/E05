package cn.edu.xmu.javaee.dao;

import cn.edu.xmu.javaee.E05Application;
import cn.edu.xmu.javaee.dao.bo.Product;
import cn.edu.xmu.javaee.dao.bo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    public void retrieveProductByName(){
        Long id =  1553L;
        String name = "欢乐家岭南杂果罐头";
        Long originalPrice = 3036L;
        Long weight = 700L;
        Integer otherProductSize = 14;
        Integer onSaleListSize = 1;
        String skuSn = null;
        String barcode = "6924254673381";
        String unit = "瓶";
        String originPlace = "广东";
        Integer commissionRatio = null;
        Long freeThreshold = -1L;
        byte status = 0;
        User creator = new User(1L, "admin");
        User modifier = new User(null, null);
        LocalDateTime gmtCreate = LocalDateTime.parse("2021-11-11T13:12:48");
        LocalDateTime gmtModified = null;

        List<Product> result = productDao.retrieveProductByName(name);

//        返回商品列表不为空
        assertThat(result).isNotEmpty();
//        返回商品列表长度为1
        assertThat(result.size()).isEqualTo(1);
//        返回商品列表与预期相同
        assertThat(result.get(0).getId()).isEqualTo(id);
        assertThat(result.get(0).getName()).isEqualTo(name);
        assertThat(result.get(0).getOriginalPrice()).isEqualTo(originalPrice);
        assertThat(result.get(0).getWeight()).isEqualTo(weight);
        assertThat(result.get(0).getOtherProduct()).hasSize(otherProductSize);
        assertThat(result.get(0).getOnSaleList()).hasSize(onSaleListSize);
        assertThat(result.get(0).getSkuSn()).isEqualTo(skuSn);
        assertThat(result.get(0).getBarcode()).isEqualTo(barcode);
        assertThat(result.get(0).getUnit()).isEqualTo(unit);
        assertThat(result.get(0).getOriginPlace()).isEqualTo(originPlace);
        assertThat(result.get(0).getCommissionRatio()).isEqualTo(commissionRatio);
        assertThat(result.get(0).getFreeThreshold()).isEqualTo(freeThreshold);
        assertThat(result.get(0).getStatus()).isEqualTo(status);
        assertThat(result.get(0).getCreator()).isEqualTo(creator);
        assertThat(result.get(0).getModifier()).isEqualTo(modifier);
        assertThat(result.get(0).getGmtCreate()).isEqualTo(gmtCreate);
        assertThat(result.get(0).getGmtModified()).isEqualTo(gmtModified);
    }

}
