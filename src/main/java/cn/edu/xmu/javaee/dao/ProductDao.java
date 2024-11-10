package cn.edu.xmu.javaee.dao;

import cn.edu.xmu.javaee.mapper.ProductMapper;
import cn.edu.xmu.javaee.core.exception.BusinessException;
import cn.edu.xmu.javaee.core.model.ReturnNo;
import cn.edu.xmu.javaee.dao.bo.OnSale;
import cn.edu.xmu.javaee.dao.bo.Product;
import cn.edu.xmu.javaee.mapper.po.ProductPo;
import cn.edu.xmu.javaee.util.CloneFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Suave
 * @date 2024/11/9
 */
@Repository
public class ProductDao {
    private final static Logger logger = LoggerFactory.getLogger(ProductDao.class);

    private ProductMapper productMapper;

    private  OnSaleDao onSaleDao;

    @Autowired
    public ProductDao (ProductMapper productMapper, OnSaleDao onSaleDao) {
        this.productMapper = productMapper;
        this.onSaleDao = onSaleDao;
    }

    /**
     * 用商品名称搜索商品
     * @param name 商品名称
     * @return 商品对象
     */
    public List<Product> retrieveProductByName(String name) {
        List<Product> productList = new ArrayList<>();
        try {
            List<ProductPo> productPoList = productMapper.findByName(name);
            for(ProductPo po:productPoList){
                Product product = retrieveFullProduct(po);
                productList.add(product);
            }
        }catch (BusinessException e){
            logger.error(e.getMessage());
            throw new BusinessException(ReturnNo.INTERNAL_SERVER_ERR, "数据库访问错误");
        }

        return productList;
    }

    /**
     *检索完整商品
     * @param productPo 商品po对象
     * @return 完整商品
     */
    private Product retrieveFullProduct(ProductPo productPo){
        assert null != productPo;
        Product product = CloneFactory.copy(new Product(),productPo);
        logger.debug("retrieveFullProduct: product = {}",product);
        List<OnSale> onSaleList = onSaleDao.getLatestOnSale(productPo.getId());
        product.setOnSaleList(onSaleList);

        List<Product> otherProduct = this.retrieveOtherProducts(productPo);
        product.setOtherProduct(otherProduct);
        return product;
    }

    /**
     * 检索其他关联商品
     * @param productPo 商品po对象
     * @return 其他商品列表
     */
    private List<Product> retrieveOtherProducts(ProductPo productPo){
        assert null != productPo;
        List<ProductPo> productPoList = new ArrayList<>();
        try{
            productPoList = productMapper.findByGoodsIdEqualsAndIdNot(productPo.getGoodsId(),productPo.getId());
        }catch (BusinessException e){
            logger.error(e.getMessage());
            throw new BusinessException(ReturnNo.INTERNAL_SERVER_ERR, "数据库访问错误");
        }
        return productPoList.stream().map((po -> CloneFactory.copy(new Product(),po))).collect(Collectors.toList());
    }
}
