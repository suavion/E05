package cn.edu.xmu.javaee.mapper;

import cn.edu.xmu.javaee.mapper.po.OnSalePo;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Suave
 * @date 2024/11/9
 */
@Repository
public interface OnSaleMapper extends JpaRepository<OnSalePo,Long> {
    List<OnSalePo> findByProductIdEqualsAndBeginTimeBeforeAndEndTimeAfter(Long productId, LocalDateTime beginTime, LocalDateTime endTime, Sort sort);
}