package com.example.simulationproject.common;

import lombok.Data;
import java.util.HashMap;
import java.util.Map;
//common return data
@Data
public class R<T> {
    //encode, 1 success, others fail
    private Integer code;
    //error message
    private String msg;
    //data
    private T data;
    //dynamic data
    private Map map = new HashMap();

    public static <T> R<T> success(T object) {
        R<T> r = new R<T>();
        r.data = object;
        r.code = 1;
        return r;
    }

    public static <T> R<T> error(String msg) {
        R r = new R();
        r.msg = msg;
        r.code = 0;
        return r;
    }

    public R<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

}
