package com.xisuo.sellmanager.service.impl;

import com.xisuo.sellmanager.dao.ProductDao;
import com.xisuo.sellmanager.entity.Product;
import com.xisuo.sellmanager.interceptor.UserContext;
import com.xisuo.sellmanager.service.ProductService;
import com.xisuo.sellmanager.utils.NumUtil;
import com.xisuo.sellmanager.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zk
 * @Description: 产品服务类
 * @date 2021-07-05 14:22
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    private static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    //产品编号,后边的加上一个数字,防止重复
    private AtomicInteger num = new AtomicInteger(0);

    @Override
    public Product getProductById(Long id) {
        return productDao.queryProductById(id);
    }

    @Override
    public void createProduct(Product product) {
        logger.info("用户:{} 创建了产品:{}", UserContext.getUser().getId(), product.getName());
        product.setCreateTime(new Date());
        productDao.save(product);
    }


    @Override
    public void modifyProduct(Product product) {
        logger.info("用户:{} 修改了产品:{}", UserContext.getUser().getId(), product.getName());
        productDao.modifyProduct(product);
    }


    @Override
    public void deleteProduct(Long id) {
        logger.info("用户:{} 删除了产品:{}", UserContext.getUser().getId(), id);
        productDao.deleteProduct(id);
    }


    @Override
    public Page<Product> queryAllProduct(Map<String, Object> params) {
        List<Product> list = productDao.queryAllProduct(params);
        int num = productDao.queryAllProductNum(params);
        Page<Product> page = new Page<Product>(NumUtil.num2Int(params.get("pageNo")), num, list);
        return page;
    }


    @Override
    public List<Map<String, Object>> queryProducts() {
        return productDao.queryProducts();
    }

    @Override
    public List<Long> queryProductIds(List<String> productNames) {
        return productDao.queryProductIds(productNames);
    }

    @Override
    public List<String> searchName(String name) {
        return productDao.searchName("%" + name + "%");
    }


    @Override
    public String productService(List<String> productNames) {
        List<Map<String, Object>> list = queryProducts();
        List<String> names = new ArrayList<>(list.size());
        for (Map<String, Object> map : list) {
            String value = String.valueOf(map.get("name"));
            names.add(value);
        }
        StringBuilder sb = new StringBuilder();
        for (String name : productNames) {
            if (!names.contains(name)) {
                sb.append(name);
                sb.append(",");
            }
        }
        if (sb.length() > 0) {
            return sb.substring(0, sb.length() - 1);
        } else {
            return "0";
        }
    }

    @Override
    public int findName(String name) {
        return productDao.findName(name);
    }


}
