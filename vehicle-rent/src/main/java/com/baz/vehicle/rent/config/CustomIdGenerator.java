package com.baz.vehicle.rent.config;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

public class CustomIdGenerator implements IdentifierGenerator {

    private static final AtomicLong counter = new AtomicLong(1000);

    @Override
    public Object generate(
            SharedSessionContractImplementor session,
            Object object) {

        return counter.getAndIncrement();
    }
}