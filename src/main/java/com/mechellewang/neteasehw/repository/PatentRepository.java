package com.mechellewang.neteasehw.repository;

import com.mechellewang.neteasehw.domian.Patent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 专利持久化层
 */
public interface PatentRepository extends JpaRepository<Patent, Long>, FulltextSearchRepository<Patent> {

    Patent findByPatentId(String patentId);

//    @Query(value = "select * from patent where DATE >= ?1 and DATE <= ?2", nativeQuery = true)
    Page<Patent> findByDatePublicationBetween(Date d1, Date d2, Pageable pageable);

    Page<Patent> findByInventorContainingIgnoreCase(String inventor, Pageable pageable);

    Page<Patent> findByDatePublicationBetweenAndInventorContainingIgnoreCase(Date d1, Date d2, String inventor, Pageable pageable);

    Patent save(Patent patent);

    @Modifying
    @Transactional
    void deleteByPatentId(String patentId);

    @Modifying
    @Transactional
    @Query(value = "update patent set date = ?2, application_type = ?3, ipc_section = ?4, ipc_class = ?5, inventor = ?6, abstract = ?7, description = ?8, dsp_background = ?9, dsp_summary = ?10 where patent_id =  ?1",
            nativeQuery = true)
    void updatePatentByPatentId(String patentId, Date date, String applicationType, String ipcSection, String ipcClass, String inventor,
                      String pAbstract, String description, String dspBackground, String dspSummary);
}
