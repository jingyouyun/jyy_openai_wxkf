package cn.jingyouyun.openai.wxkf.utils;

import jodd.bean.BeanCopy;

import java.util.LinkedList;
import java.util.List;

/**
 * Created: 2020-07-08 15:51
 * author: zhujingchun
 * desc: BeanUtils bean 拷贝
 * version: 1.0
 */
public class BeanUtils {

    public static <M, T> T copyBean(M m, Class<T> clazz) {
        T t = null;
        if (m != null) {
            try {
                t = clazz.newInstance();
                BeanCopy.from(m).to(t).ignoreNulls(true).copy();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return t;
    }

    public static <M, T> T copyBean(M m, T t) {
        if (m == null) {
            return t;
        }
        if (t == null) {
            throw new NullPointerException(String.valueOf("目标对象不能为空"));
        }
        BeanCopy.from(m).to(t).ignoreNulls(true).copy();
        return t;
    }

    public static <M, T> List<T> copyBean(List<M> m, Class<T> clazz) {
        List<T> t = new LinkedList<>();
        if (m != null){
            m.forEach(e -> t.add(copyBean(e, clazz)));
        }
        return t;
    }
}
