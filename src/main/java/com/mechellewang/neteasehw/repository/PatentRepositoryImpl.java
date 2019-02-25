package com.mechellewang.neteasehw.repository;

import com.mechellewang.neteasehw.domian.Patent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class PatentRepositoryImpl implements FulltextSearchRepository<Patent> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Page<Patent> findByFulltext(String str) {
//        List<String> columns = new ArrayList<>(Arrays.asList("abstract", "description", "dsp_background", "dsp_summary"));
        Query query = em.createNativeQuery(
                "select * from patent where match (abstract, description, dsp_background, dsp_summary) " +
                        "against ('+"+str+"' in BOOLEAN MODE)");
        Page<Patent> patents = new PageImpl<>(query.getResultList());
        return patents;
    }
}
