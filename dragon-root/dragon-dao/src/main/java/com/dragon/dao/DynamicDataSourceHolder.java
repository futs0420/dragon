package com.dragon.dao;

public class DynamicDataSourceHolder {
	  
    private static final ThreadLocal<String> holder = new ThreadLocal<String>();   
       
    public static void putDataSourceName(String name){   
        holder.set(name);   
    }   
       
    public static String getDataSourceName(){   
        return holder.get();   
    }   

}
