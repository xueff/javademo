package plain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

//邮件提醒


public class Plain {

    @GeneratedValue
    @Id
    private Integer id = 0;
    @Column(columnDefinition = "varchar(50) COMMENT '任务名称'")
    private String name;
    @Column(columnDefinition = "varchar(50) COMMENT '周期单位：年月周日时'")
    private String cycleTimeUnit;
    @Column(columnDefinition = "int(10) COMMENT '周期数量'")
    private Integer cycleTime;
    @Column(columnDefinition = "varchar(10) COMMENT '任务类型'")
    private String plainType;
//    @Column(columnDefinition = "varchar(10) COMMENT '任务级别'")
//    private String plainType;

    @Column(columnDefinition = "int(10) COMMENT 'PlainAction初始化数量'")
    private Integer addNumber;
    @Column(columnDefinition = "int(10) COMMENT '任务分数'")
    private Integer percent;
    @Column(columnDefinition = "boolean COMMENT '是否启用'")
    private Boolean hasUse;


}

class PlainAction {
    @GeneratedValue
    @Id
    private Integer id = 0;
    @Column(columnDefinition = "varchar(50) COMMENT '任务名称： endtime_任务名'")
    private String name;
    @Column(columnDefinition = "varchar(50) COMMENT '周期单位：年月周日时'")
    private String cycleTimeUnit;
    @Column(columnDefinition = "int(10) COMMENT '周期数量'")
    private Integer cycleTime;

    @Column(columnDefinition = "int(11) COMMENT '任务状态：//任务状态：0-(未开始）、10-进行中，20-任务暂停，40-任务完成'")
    private Integer status;

    @Column(columnDefinition = "int(10) COMMENT '任务完成度 0-100'")
    private Integer  completionDegree;
    @Column(columnDefinition = "int(10) COMMENT '任务得分：任务度*Plain.percent'")
    private Double  percentGet;
    @Column(columnDefinition = "int(10) COMMENT '任务开始时间'")
    private Integer startTime;
    @Column(columnDefinition = "int(10) COMMENT '完成任务时间'")
    private Integer finishTime;

}

//特别统计
class PlainClassification {

    @Column(columnDefinition = "varchar(50) COMMENT '统计名称'")
    private String name;
    @Column(columnDefinition = "varchar(1000) COMMENT '任务id'")
    private String plainIds;

}
