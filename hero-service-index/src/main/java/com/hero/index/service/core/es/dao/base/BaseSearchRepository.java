package com.hero.index.service.core.es.dao.base;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

/**
 * es搜索父类,封装CURD操作,相当于dao使用
 *
 * @param <E>
 * @param <ID>
 */
public interface BaseSearchRepository<E, ID extends Serializable> extends ElasticsearchRepository<E, ID>, PagingAndSortingRepository<E, ID> {


}
