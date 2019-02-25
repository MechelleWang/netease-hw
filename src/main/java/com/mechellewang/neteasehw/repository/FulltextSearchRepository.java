package com.mechellewang.neteasehw.repository;

import org.springframework.data.domain.Page;

public interface FulltextSearchRepository<T> {
    /**
     * 自定义全文检索方法
     * @return
     */
    <S extends T> Page<S> findByFulltext(String str);
}
