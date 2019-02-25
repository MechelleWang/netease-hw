package com.mechellewang.neteasehw.service;

import com.mechellewang.neteasehw.domian.Patent;
import com.mechellewang.neteasehw.repository.PatentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service("patentService")
public class PatentServiceImpl implements PatentService {

    private PatentRepository patentRepository;

    public PatentServiceImpl(PatentRepository patentRepository) {
        this.patentRepository = patentRepository;
    }

    @Override
    public Patent getPatent(String patentId) {
        Assert.notNull(patentId, "patentId不能为空");
        return patentRepository.findByPatentId(patentId);
    }

    @Override
    public Page<Patent> getPatents(String keyword, Date d1, Date d2, String inventor, Pageable pageable) {
        if (StringUtils.isEmpty(keyword) && StringUtils.isEmpty(d1) && StringUtils.isEmpty(d2) && StringUtils.isEmpty(inventor)) {
            Assert.notNull(keyword, "参数全为空");
        }
        if (!StringUtils.hasText(keyword)) {
            if (StringUtils.isEmpty(d1) && StringUtils.isEmpty(d2)) {
                return patentRepository.findByInventorContainingIgnoreCase(inventor, pageable);
            }
            if (StringUtils.isEmpty(d1)) {
                d1 = d2;
            } else if (StringUtils.isEmpty(d2)) {
                d2 = d1;
            }
            if (StringUtils.isEmpty(inventor)) {
                return patentRepository.findByDatePublicationBetween(d1, d2, pageable);
            }
        } else {
            // TODO: 2019-02-19
            return patentRepository.findByFulltext(keyword);
        }
        return patentRepository.findByDatePublicationBetweenAndInventorContainingIgnoreCase(d1, d2, inventor, pageable);
    }

    @Override
    public Patent savePatent(Patent patent) {
        Patent p = patentRepository.findByPatentId(patent.getPatentId());
        Assert.isNull(p, "已存在patent: " + patent.getPatentId());
        return patentRepository.save(patent);
    }

    @Override
    public boolean deletePatent(String patentId) {
        Patent patent = patentRepository.findByPatentId(patentId);
        Assert.notNull(patent, "查询不到patent: " + patentId);
        patentRepository.deleteByPatentId(patentId);
        patent = patentRepository.findByPatentId(patentId);
        return StringUtils.isEmpty(patent);
    }
}
