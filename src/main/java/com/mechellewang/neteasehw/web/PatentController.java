package com.mechellewang.neteasehw.web;

import com.mechellewang.neteasehw.domian.Patent;
import com.mechellewang.neteasehw.service.PatentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
public class PatentController {
    @Autowired
    private PatentService patentService;

    @RequestMapping("/add")
    public String add() throws ParseException {
        Patent patent = new Patent();
        patent.setPatentId("USD0806350-20180102");
        Date date = new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2018-01-02").getTime());
        patent.setDatePublication(date);
        patent.setApplicationType("design");
        patent.setInventor("Armelle Lecointe, Laurent Bisson");
        patentService.savePatent(patent);
        return "add ok";
    }

    @RequestMapping("/find")
    public Page<Patent> find() throws ParseException {
        Date date = new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2018-01-02").getTime());
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(0, 15, sort);
        return patentService.getPatents(null, date, date, null, pageable);
    }

    @RequestMapping("/remove")
    public boolean remove() {
        return patentService.deletePatent("USD0806350-20180102");
    }

    @RequestMapping("/ft")
    public Page<Patent> fulltext() {
        return patentService.getPatents("photograph", null, null, null, new PageRequest(0, 15));
    }
}
