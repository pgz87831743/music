package jx.pgz.dao.yw.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2023-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Position implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @Excel(name = "id")
    private Long id;

    @Excel(name = "address")
    private String address;

    @Excel(name = "x")
    private Double x;

    @Excel(name = "y")
    private Double y;

    @Excel(name = "z")
    private Double z;

    @Excel(name = "stay")
    private Integer stay;

    @Excel(name = "timestamp")
    private Long timestamp;

    @Excel(name = "bs_address")
    private Long bsAddress;

    @Excel(name = "sample_time")
    private String sampleTime;

    @Excel(name = "sample_batch")
    private Integer sampleBatch;


}
