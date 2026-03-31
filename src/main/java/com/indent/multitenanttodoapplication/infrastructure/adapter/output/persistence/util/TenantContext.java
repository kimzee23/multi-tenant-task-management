package com.indent.multitenanttodoapplication.infrastructure.adapter.output.persistence.util;

public class TenantContext {
    private static ThreadLocal<String> tenantHolder = new ThreadLocal<>();

    public static void setTenantId(String tenantId){
        tenantHolder.set(tenantId);
    }
    public static String getTenantId(){
        return tenantHolder.get();
    }
    public static void clear(){
        tenantHolder.remove();
    }
}
