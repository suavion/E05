package cn.edu.xmu.javaee.dao;

import cn.edu.xmu.javaee.E05Application;
import cn.edu.xmu.javaee.dao.bo.OnSale;
import cn.edu.xmu.javaee.dao.bo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Suave
 * @date 2024/11/9
 */
@SpringBootTest(classes = E05Application.class)
@Transactional
public class OnSaleDaoTest {
    private OnSaleDao onSaleDao;

    @Autowired
    public OnSaleDaoTest(OnSaleDao onSaleDao) {this.onSaleDao = onSaleDao;}

    @Test
    public void getLatestOnSale(){
        Long productId = 1553L;
        List<OnSale> onSaleList = new ArrayList<>();
        OnSale onSale = OnSale.builder()
                .id(4L)
                .price(1027L)
                .beginTime(LocalDateTime.parse("2021-11-11T14:38:20"))
                .endTime(LocalDateTime.parse("2030-02-19T14:38:20"))
                .quantity(32)
                .maxQuantity(50)
                .creator(new User(1L, "admin"))
                .modifier(new User(null, null))
                .gmtCreate(LocalDateTime.parse("2021-11-11T14:38:20"))
                .gmtModified(null)
                .build();
        onSaleList.add(onSale);
        List<OnSale> result = onSaleDao.getLatestOnSale(productId);
//        返回销售列表不为空
        assertThat(result).isNotEmpty();
//        返回销售列表长度为1
        assertThat(result.size()).isEqualTo(1);
//        返回销售列表与预期相同
        assertThat(result).isEqualTo(onSaleList);
    }


}
