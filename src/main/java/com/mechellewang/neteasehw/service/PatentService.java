package com.mechellewang.neteasehw.service;

import com.mechellewang.neteasehw.domian.Patent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;


public interface PatentService {
    Patent getPatent(String patentId);
    Page<Patent> getPatents(String keyword, Date d1, Date d2, String inventor, Pageable pageable);
    Patent savePatent(Patent patent);
    boolean deletePatent(String patentId);
}
