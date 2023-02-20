package jx.pgz.model.dto;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO {

    @ApiModelProperty(value = "当前页", example = "1")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页记录数", example = "10")
    private Integer pageSize = 10;


    /**
     * 获取页面对象
     *
     * @return
     */
    @ApiModelProperty(hidden = true)
    public <E> Page<E> getMybatisPage() {
        Page<E> page = new Page<>();

        if (pageNum != null) {
            page.setCurrent(pageNum);
        }

        if (pageSize != null) {
            page.setSize(pageSize);
        }

        return page;
    }





}
