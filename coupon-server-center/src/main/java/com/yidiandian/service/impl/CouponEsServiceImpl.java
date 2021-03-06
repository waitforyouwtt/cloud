package com.yidiandian.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yidiandian.config.Constants;
import com.yidiandian.dao.CouponDao;
import com.yidiandian.entity.Coupon;
import com.yidiandian.service.CouponEsService;
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
public class CouponEsServiceImpl implements CouponEsService {

    @Resource
    private CouponDao couponDao;

    @Autowired
    RestHighLevelClient restHighLevelClient;

    /**
     * 创建优惠券es索引库[不存在则创建，存在则修改]
     */
    @Override
    public void createIndex() {
        List<Coupon> couponList = couponDao.queryAllByLimit(0,10);

        if (CollectionUtils.isEmpty(couponList)) {
            return;
        }

        couponList.parallelStream().forEach(coupon -> {
            //文档内容
            //准备json数据
            // Map map = JSONObject.parseObject(JSONObject.toJSONString(projectInfo), Map.class);
            Map<String,Object> jsonMap = JSONObject.parseObject(JSON.toJSONString(coupon));
            //创建索引创建对象
            IndexRequest indexRequest = new IndexRequest(Constants.COUPON_ES_INDEX, Constants.ES_TYPE,coupon.getId()+"");
            //文档内容
            indexRequest.source(jsonMap);
            //通过client进行http的请求
            IndexResponse indexResponse = null;
            try {
                indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            } catch (Exception e) {
                log.error("创建优惠券es索引库发生异常：{}",e.getMessage());
            }
            DocWriteResponse.Result result = indexResponse.getResult();
            log.info("创建优惠券es索引库的结果是：{}",JSON.toJSONString(result));
        });
    }

    /**
     * 保存或更新ES
     */
    @Override
    public void saveOrUpdateES() throws InterruptedException {
        Coupon params = new Coupon();
        List<Coupon> couponList = couponDao.queryAll(params);
        List<List<Coupon>> partitions = ListUtils.partition(couponList, 10);
        if (CollectionUtils.isEmpty(partitions)) {
            return;
        }

        valid: for (List<Coupon> coupons : partitions){
            if (CollectionUtils.isEmpty(coupons)) {
                continue valid;
            }
            coupons.parallelStream().forEach(coupon -> {
                Map<String, Object> jsonMap = JSONObject.parseObject(JSON.toJSONString(coupon));
                //创建索引创建对象
                IndexRequest indexRequest = new IndexRequest(Constants.COUPON_ES_INDEX, Constants.ES_TYPE);
                //文档内容
                indexRequest.source(jsonMap);
                //组装参数
                UpdateRequest updateRequest = new UpdateRequest(Constants.COUPON_ES_INDEX, coupon.getId() + "").doc(jsonMap).upsert(indexRequest);
                UpdateResponse update = null;
                try {
                    //通过client进行http的请求
                    update = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
                    DocWriteResponse.Result result = update.getResult();
                    log.info("保存或更新ES索引库的结果是：{}", JSON.toJSONString(result));
                } catch (Exception e) {
                    log.error("保存或更新ES索引库发生异常：{}", e.getMessage());
                }
            });
        }
    }

    /**
     * 根据Id 进行删除
     *
     * @param id
     */
    @Override
    public void deleteById(String id) {

    }

    /**
     * 通过id 查询ES
     *
     * @param id
     * @return
     */
    @Override
    public Coupon queryProjectESById(String id) {
        //搜索请求对象
        SearchRequest searchRequest = new SearchRequest(Constants.COUPON_ES_INDEX);
        //指定类型
        searchRequest.types(Constants.ES_TYPE);
        //搜索源构建对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //搜索方式
        //MatchQuery
        searchSourceBuilder.query(QueryBuilders.matchQuery("id",id).minimumShouldMatch("80%"));

        //向搜索请求对象中设置搜索源
        searchRequest.source(searchSourceBuilder);
        //执行搜索,向ES发起http请求
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.error("通过database Id查询ES索引库发生异常：{}",e.getMessage());
        }
        //搜索结果
        SearchHits hits = searchResponse.getHits();
        //匹配到的总记录数
        TotalHits totalHits = hits.getTotalHits();
        //得到匹配度高的文档
        SearchHit[] searchHits = hits.getHits();
        //日期格式化对象
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Coupon coupon =  new Coupon();

        for(SearchHit hit:searchHits){
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            coupon = JSON.parseObject(JSON.toJSONString(sourceAsMap), Coupon.class);
        }
        return coupon;
    }

}