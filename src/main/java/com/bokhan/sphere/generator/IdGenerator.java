package com.bokhan.sphere.generator;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by vbokh on 31.05.2017.
 */
public class IdGenerator {
    private static AtomicInteger uniqueId = new AtomicInteger(0);

    public static int nextId() {
        return uniqueId.incrementAndGet();
    }
}
