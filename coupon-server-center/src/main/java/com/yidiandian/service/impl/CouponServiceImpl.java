package com.yidiandian.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yidiandian.config.Constants;
import com.yidiandian.entity.Coupon;
import com.yidiandian.dao.CouponDao;
import com.yidiandian.service.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * (Coupon)表服务实现类
 *
 * @author makejava
 * @since 2020-07-16 16:23:16
 */
@Service
@Slf4j
public class CouponServiceImpl implements CouponService {

    @Resource
    private CouponDao couponDao;

    @Autowired
    RestHighLevelClient restHighLevelClient;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Coupon queryById(Integer id) {
        return this.couponDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Coupon> queryAllByLimit(int offset, int limit) {
        return this.couponDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param coupon 实例对象
     * @return 实例对象
     */
    @Override
    public Coupon insert(Coupon coupon) {
        this.couponDao.insert(coupon);
        return coupon;
    }

    /**
     * 修改数据
     *
     * @param coupon 实例对象
     * @return 实例对象
     */
    @Override
    public Coupon update(Coupon coupon) {
        this.couponDao.update(coupon);
        return this.queryById(coupon.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.couponDao.deleteById(id) > 0;
    }


    /**
     * 批量增加
     *
     * @param entityList
     * @return
     */
    @Override
    public void batchInsert(List<Coupon> entityList) {
        couponDao.batchInsert(entityList);
    }
}