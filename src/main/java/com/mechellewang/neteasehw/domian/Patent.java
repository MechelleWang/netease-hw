package com.mechellewang.neteasehw.domian;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Table(name = "patent")
//, uniqueConstraints = {@UniqueConstraint(columnNames = {"ipc_section", "ipc_class"})}
@Entity
public class Patent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "patent_id", unique = true, nullable = false, length = 19)
    private String patentId;
    @Column(name = "date_publication", nullable = false)
    private Date datePublication;
    @Column(name = "application_type", nullable = false, length = 10)
    private String applicationType;
    @Column(name = "ipc_section", length = 1)
    private String ipcSection;
    @Column(name = "ipc_class", length = 2)
    private String ipcClass;
    @Column(name = "inventor", nullable = false, columnDefinition = "text")
    private String inventor;
    @Column(nullable = false, columnDefinition = "text")
    private String title;
    @Column(name = "abstract", columnDefinition = "text")
    private String pAbstract;
    @Lob
    @Column(name = "dsp_background")
    private String dspBackground;
    @Lob
    @Column(name = "dsp_summary")
    private String dspSummary;
    @Column(name = "create_time", insertable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createTime;
    @Column(name = "update_time", insertable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updatedTime;
}
