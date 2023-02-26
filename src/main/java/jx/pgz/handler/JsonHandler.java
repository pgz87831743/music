package jx.pgz.handler;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

@MappedTypes({Object.class})
@MappedJdbcTypes(JdbcType.VARCHAR)
public class JsonHandler extends JacksonTypeHandler {

    private Class<?> type;
    public JsonHandler(Class<?> type) {
        super(type);
        this.type=type;
    }

    @Override
    protected Object parse(String json) {
        return JSON.parseObject(json,type);
    }

    @Override
    protected String toJson(Object obj) {
        return JSON.toJSONString(obj);
    }
}
