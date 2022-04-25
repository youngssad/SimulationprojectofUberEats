package com.example.simulationproject.common;


//tool class
//base on Thread Local class
//get the current login user id
//every request will open a thread
public class BaseContext {
    private static ThreadLocal<Long> threadLocal= new ThreadLocal<>();
    public static void setCurrentId(Long id){
        threadLocal.set(id);

    }
    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
