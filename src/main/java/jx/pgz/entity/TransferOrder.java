package jx.pgz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 调拨单
 * </p>
 *
 * @author admin
 * @since 2023
 */

@TableName("transfer_order")
@ApiModel(value = "TransferOrder对象", description = "调拨单")
public class TransferOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("零件编号")
    private Long partId;

    @ApiModelProperty("调拨数量")
    private Integer num;

    @ApiModelProperty("汽车编号")
    private Long carId;


    public Long getPartId() {
        return partId;
    }

    public void setPartId(Long partId) {
        this.partId = partId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }
}
