package com.zhanghe.stockSimulating.Util;

import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class IDGenerator {

    private String machineId;
    private short machineHash;

    private AtomicInteger counter;
    private long lastTimestamp;

    private ByteBuffer byteBuffer;
    private AtomicBoolean lock;

    IDGenerator(String machineId) {
        this.machineId = machineId;
        machineHash = (short) (machineId.hashCode() >> 16);
        // 【TODO】 validate machineId here to avoid collision
        lock = new AtomicBoolean(false);
        counter = new AtomicInteger(0);
        lastTimestamp = System.currentTimeMillis();
        byteBuffer = ByteBuffer.allocate(16);
    }

    /*
     *   decodeOrderId
     *   @description generate a new id
     * */

    byte[] generateOrderId() {

        long timestamp = System.currentTimeMillis();

        while (lock.getAndSet(true)) {  // get spin_lock
            try {
                Thread.sleep(0,1);
            }catch (InterruptedException e){}
        }

        if (lastTimestamp != timestamp) {
            lastTimestamp = timestamp;
            counter.set(0);
        }
        lock.set(false);                    // release spin_lock

        byteBuffer.clear();
        byteBuffer.putLong(lastTimestamp);
        byteBuffer.putShort(machineHash);
        byteBuffer.putInt(counter.getAndIncrement());
        return byteBuffer.array().clone();
    }

    /*
    *   decodeOrderId
    *
    *   @param id generated id bytes
    *   @description decode the generated id bytes to String
    * */

    public String decodeOrderId(byte[] id) {

        assert id.length == 16: "Wrong id length";

        ByteBuffer buffer = ByteBuffer.wrap(id);

        return String.format("%016x%04x%08x", buffer.getLong(), buffer.getShort(), buffer.getInt());
    }
}
