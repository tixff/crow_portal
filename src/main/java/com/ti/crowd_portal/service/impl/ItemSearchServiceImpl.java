package com.ti.crowd_portal.service.impl;

import com.ti.crowd_manager.domain.Item;
import com.ti.crowd_manager.domain.parameter.PageQuery;
import com.ti.crowd_manager.domain.result.PageResult;
import com.ti.crowd_portal.service.ItemSearchService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Ti
 * @date 2019/1/30
 */

@Service
public class ItemSearchServiceImpl implements ItemSearchService {

    private Logger logger = LoggerFactory.getLogger(ItemSearchServiceImpl.class);
    @Autowired
    private SolrClient client;

    @Override
    public PageResult<Item> searchItemByItemTitle(PageQuery query) {
        PageResult<Item> pageResult = new PageResult<>();
        ArrayList<Item> items = new ArrayList<>();
        Integer start = (query.getCurrentPage() - 1) * query.getRows();
        Integer end = query.getRows();
        try {
            SolrQuery params = new SolrQuery();

            //查询条件, 这里的 q 对应 下面图片标红的地方
            params.set("q", query.getQuery());

            //过滤条件
            //params.set("fq", "product_price:[100 TO 100000]");

            //排序
            //params.addSort("product_price", SolrQuery.ORDER.asc);

            //分页
            params.setStart(start);
            params.setRows(end);

            //默认域
            params.set("df", "short_title");

            //只查询指定域
            //params.set("fl", "id,name,image,short_title");

            //高亮
            //打开开关
            params.setHighlight(true);
            //指定高亮域
            params.addHighlightField("short_title");
            //设置前缀
            params.setHighlightSimplePre("<em style='color: red'>");
            //设置后缀
            params.setHighlightSimplePost("</em>");

            QueryResponse queryResponse = client.query(params);

            SolrDocumentList results = queryResponse.getResults();

            long numFound = results.getNumFound();

            //获取高亮显示的结果, 高亮显示的结果和查询结果是分开放的(key:id value:[key:高亮字段 value:值])
            Map<String, Map<String, List<String>>> highlight = queryResponse.getHighlighting();

            for (SolrDocument result : results) {
                Item item = new Item();
                Integer id = Integer.valueOf((String) result.get("id"));
                item.setId(id);
                item.setName((String) result.get("name"));
                List<String> highList = highlight.get(result.get("id")).get("short_title");
                if (highList != null && highList.size() > 0) {
                    item.setShortTitle(highList.get(0));
                } else {
                    item.setShortTitle((String) result.get("short_title"));
                }
                item.setFinancingDays(Integer.valueOf((String) result.get("financing_days")));
                item.setImage((String) result.get("image"));
                item.setCreateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse((String) result.get("create_time")));
                item.setContributeNum(Integer.valueOf((String) result.get("contribute_num")));
                item.setCurrentMoney(Double.valueOf((String) result.get("current_money")));
                item.setRaiseMoney(Double.valueOf((String) result.get("raise_money")));
                item.setIntro((String) result.get("intro"));
                items.add(item);
            }
            pageResult.setCurrentPage(query.getCurrentPage());
            pageResult.setTotalCount((int) numFound);
            pageResult.setData(items);
            long totalPage = (numFound % query.getRows()) > 0 ? numFound / query.getRows() + 1 : numFound / query.getRows();
            pageResult.setTotalPage((int) totalPage);
            pageResult.setData(items);
            return pageResult;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询出错：{}", e.getStackTrace());
        }

        return null;
    }

    @Override
    public Item searchItemById(Integer id) {
        SolrQuery params = new SolrQuery();
        //查询条件, 这里的 q 对应 下面图片标红的地方
        params.set("q", id);
        //默认域
        params.set("df", "id");
        try {
            QueryResponse queryResponse = client.query(params);
            SolrDocumentList results = queryResponse.getResults();
            Item item = new Item();
            for (SolrDocument document : results) {
                Integer itemId = Integer.valueOf((String) document.get("id"));
                String name = (String) document.get("name");
                item.setId(itemId);
                item.setName(name);
                item.setShortTitle((String) document.get("short_title"));
                item.setFinancingDays(Integer.valueOf((String) document.get("financing_days")));
                item.setImage((String) document.get("image"));
                item.setCreateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse((String) document.get("create_time")));
                item.setContributeNum(Integer.valueOf((String) document.get("contribute_num")));
                item.setCurrentMoney(Double.valueOf((String) document.get("current_money")));
                item.setRaiseMoney(Double.valueOf((String) document.get("raise_money")));
                item.setIntro((String) document.get("intro"));
                item.setLinkmanName((String) document.get("linkman_name"));
            }
            return item;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ArrayList<Item> searchHotItem() {
        ArrayList<Item> itemList = new ArrayList<>();
        SolrQuery params = new SolrQuery();
        //查询条件, 这里的 q 对应 下面图片标红的地方
        params.set("q", "*:*");
        //默认域
        params.set("df", "short_title");
        //排序
        params.addSort("contribute_num", SolrQuery.ORDER.desc);
        params.setStart(0);
        params.setRows(4);
        try {
            QueryResponse queryResponse = client.query(params);
            SolrDocumentList results = queryResponse.getResults();
            for (SolrDocument document : results) {
                Item item = new Item();
                Integer itemId = Integer.valueOf((String) document.get("id"));
                String name = (String) document.get("name");
                item.setId(itemId);
                item.setName(name);
                item.setShortTitle((String) document.get("short_title"));
                item.setFinancingDays(Integer.valueOf((String) document.get("financing_days")));
                item.setImage((String) document.get("image"));
                item.setCreateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse((String) document.get("create_time")));
                item.setContributeNum(Integer.valueOf((String) document.get("contribute_num")));
                item.setCurrentMoney(Double.valueOf((String) document.get("current_money")));
                item.setRaiseMoney(Double.valueOf((String) document.get("raise_money")));
                item.setIntro((String) document.get("intro"));
                itemList.add(item);
            }
            return itemList;
        } catch (Exception e) {
            return null;
        }
    }
}
