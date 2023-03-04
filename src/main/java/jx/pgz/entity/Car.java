package jx.pgz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 汽车表
 * </p>
 *
 * @author admin
 * @since 2023
 */

@ApiModel(value = "Car对象", description = "汽车表")
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("汽车编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("汽车名称")
    private String name;

    @ApiModelProperty("检查明细")
    private List<Report> reportList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Report> getReportList() {
        return reportList;
    }

    public void setReportList(List<Report> reportList) {
        this.reportList = reportList;
    }
}
