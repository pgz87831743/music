package jx.pgz.dao.yw.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableName;
import jx.pgz.dao.sys.entity.SysFile;
import jx.pgz.handler.JsonArrayHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.ArrayTypeHandler;

/**
 * <p>
 * 培训报表
 * </p>

 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(autoResultMap = true)
public class ReportForms implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 部门
     */
    private String f1;

    /**
     * 培训对象
     */
    private String f2;

    /**
     * 培训标题
     */
    private String f3;

    /**
     * 培训类型
     */
    private String f4;

    /**
     * 培训费用(元)
     */
    private String f5;

    /**
     * 培训日期
     */
    private String f6;

    /**
     * 培训课时(h)
     */
    private String f7;

    /**
     * 培训讲师
     */
    private String f8;

    /**
     * 培训机构
     */
    private String f9;

    /**
     * 培训原因
     */
    private String f10;

    /**
     * 佐证资料
     */
    private String f11;

    /**
     * 上传附件
     */
    @TableField(typeHandler = JsonArrayHandler.class,value = "f12")
    private List<SysFile> f12;

    /**
     * 其它说明
     */
    private String f13;

    private String f14;


}
