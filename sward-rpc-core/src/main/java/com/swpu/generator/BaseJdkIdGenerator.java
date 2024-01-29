package com.swpu.generator;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhengjian
 * @date 2024-01-29 23:37
 */
public class BaseJdkIdGenerator implements IdGenerator{

    private final AtomicInteger atomicInteger;

    public BaseJdkIdGenerator(){
        atomicInteger=new AtomicInteger(1);
    }

    @Override
    public int getNextId() {
        return atomicInteger.getAndIncrement();
    }
}
