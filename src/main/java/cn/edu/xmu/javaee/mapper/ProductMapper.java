package cn.edu.xmu.javaee.mapper;

import cn.edu.xmu.javaee.mapper.po.ProductPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Suave
 * @date 2024/11/9
 */
@Repository
public interface ProductMapper extends JpaRepository<ProductPo,Long> {
    List<ProductPo> findByName(String name);
    List<ProductPo> findByGoodsIdEqualsAndIdNot(Long goodsId, Long id);
}
