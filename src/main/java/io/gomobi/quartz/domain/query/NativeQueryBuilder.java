//package io.gomobi.quartz.domain.query;
//
//public class NativeQueryBuilder {
//
//    private Class<?> ormClass;
//    private String alias;
//
//    private NativeQueryBuilder(){
//
//    }
//
//    public static NativeQueryBuilder builder(Class<?> fromTableType){
//        NativeQueryBuilder nativeQueryBuilder = new NativeQueryBuilder();
//        nativeQueryBuilder.ormClass = fromTableType;
//        nativeQueryBuilder.alias = fromTableType.getSimpleName().substring(0,2);
//        return nativeQueryBuilder;
//    }
//
//    public NativeQueryBuilder select(String... columnNames){
//
//    }
//}
