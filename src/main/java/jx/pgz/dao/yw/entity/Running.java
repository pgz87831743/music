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
public class Running implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @Excel(name = "id")
    private Long id;

    @Excel(name = "address")
    private String address;

    @Excel(name = "power")
    private String power;

    @Excel(name = "heart_rate")
    private String heartRate;

    @Excel(name = "step")
    private Long step;

    @Excel(name = "temperature")
    private String temperature;

    @Excel(name = "systolic_pressure")
    private String systolicPressure;

    @Excel(name = "diastolic_pressure")
    private String diastolicPressure;

    @Excel(name = "accx")
    private Long accx;

    @Excel(name = "accy")
    private Long accy;

    @Excel(name = "gyroscopex")
    private Long gyroscopex;

    @Excel(name = "gyroscopey")
    private Long gyroscopey;

    @Excel(name = "gyroscopez")
    private Long gyroscopez;

    @Excel(name = "stay")
    private Long stay;


    @Excel(name = "sos")
    private String sos;

    @Excel(name = "elock_open_illegal_warn")
    private String elockOpenIllegalWarn;

    @Excel(name = "timestamp")
    private Long timestamp;

    @Excel(name = "bs_address")
    private String bsAddress;

    @Excel(name = "sample_time")
    private String sampleTime;

    @Excel(name = "sample_batch")
    private Long sampleBatch;


}
