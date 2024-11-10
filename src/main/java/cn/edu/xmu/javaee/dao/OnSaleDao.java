package cn.edu.xmu.javaee.dao;

import cn.edu.xmu.javaee.mapper.OnSaleMapper;
import cn.edu.xmu.javaee.core.exception.BusinessException;
import cn.edu.xmu.javaee.core.model.ReturnNo;
import cn.edu.xmu.javaee.dao.bo.OnSale;
import cn.edu.xmu.javaee.mapper.po.OnSalePo;
import cn.edu.xmu.javaee.util.CloneFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Suave
 * @date 2024/11/9
 */
@Repository
public class OnSaleDao {
    private final static Logger logger = LoggerFactory.getLogger(OnSaleDao.class);

    private OnSaleMapper onSaleMapper;

    @Autowired
    public OnSaleDao(OnSaleMapper onSaleMapper){this.onSaleMapper = onSaleMapper;}

    /**
     * 用商品id查找商品的最近销售记录
     * @param productId 商品id
     * @return 销售记录列表
     */
    public List<OnSale> getLatestOnSale(Long productId){
        LocalDateTime now = LocalDateTime.now();
        List<OnSalePo> onSalePoList = new ArrayList<>();
        try{
            Sort sort = Sort.by(Sort.Order.desc("endTime"));
            onSalePoList = onSaleMapper.findByProductIdEqualsAndBeginTimeBeforeAndEndTimeAfter(productId,now,now,sort);
        }catch (BusinessException e){
            logger.error(e.getMessage());
            throw new BusinessException(ReturnNo.INTERNAL_SERVER_ERR,"数据库访问错误");
        }
        return onSalePoList.stream().map(po -> CloneFactory.copy(new OnSale(), po)).collect(Collectors.toList());
    }
}
