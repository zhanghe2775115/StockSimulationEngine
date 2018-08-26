package com.zhanghe.stockSimulating.Util;

import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class IDGenerator {

    private String machineId;
    private short machineHash;

    private AtomicInteger counter;
    private AtomicLong lastTimestamp;

    private ByteBuffer byteBuffer;

    public IDGenerator(String machineId) {
        this.machineId = machineId;
        machineHash = (short) (machineId.hashCode() >> 16);

        // 【TODO】 validate machineId here to avoid collision

        counter = new AtomicInteger(0);
        lastTimestamp = new AtomicLong(System.currentTimeMillis());

        byteBuffer = ByteBuffer.allocate(16);
    }

    /*
     *   decodeOrderId
     *   @description generate a new id
     * */

    public byte[] generateOrderId() {

        long timestamp = System.currentTimeMillis();
        if (lastTimestamp.getAndSet(timestamp) != timestamp) {
            counter.set(0);
        }

        byteBuffer.clear();
        byteBuffer.putLong(timestamp);
        byteBuffer.putShort(machineHash);
        byteBuffer.putInt(counter.getAndIncrement());    // 每毫秒不大于 65536 个 id;
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
