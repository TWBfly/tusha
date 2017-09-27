package win.tommy.tusha.util;

/**
 * Created by tommy on 2017/9/26 0026.
 */

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GsonUtil {
    private static Gson gson = null;
    static {
        if (gson == null)
            gson = new Gson();
    }
    public GsonUtil(){}

    /**
     * 转成String类型
     * @param obj
     * @return
     */
    public static String GsonString(Object obj){
        String gsonString = null;
        if (gson != null)
            gsonString = gson.toJson(obj);
        return  gsonString;
    }

    /**
     * 转成bean
     * @param gsonString
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T GsonToBean(String gsonString,Class<T> cls){
        T t = null;
        if (gson != null)
            t = gson.fromJson(gsonString,cls);
        return t;
    }

    /**
     * 转成List
     * @param
     * @param cls
     * @param <T>
     * @return
     */
//    public static <T> List<T> GsonToList(String gsonString, Class<T> cls){
//        List<T> list = null;
//        if (gson != null)
//            list = gson.fromJson(gsonString,new TypeToken<List<T>>(){}.getType());
//        return list;
//    }
    public static <T> List<T> GsonToList(String jsonString, Class<T> cls) {
        List<T> list = new ArrayList<T>();
        try {
            if (gson!=null){
                list = gson.fromJson(jsonString, new ListOfJson(cls));
            }
        } catch (Exception e) {
        }
        return list;
    }
    public static class ListOfJson<T> implements ParameterizedType {
        private Class<?> wrapped;

        public ListOfJson(Class<T> wrapper) {
            this.wrapped = wrapper;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{wrapped};
        }

        @Override
        public Type getRawType() {
            return List.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }






}

