package com.zhanghe.stockSimulating.Util;

import org.junit.jupiter.api.Test;

class IDGeneratorTestSuit {

    @Test
    void IDGeneratorTest() {

        IDGenerator generator = new IDGenerator("localMachine");
        for( int i=0; i< 1E6; i++) {
            generator.generateOrderId();
        }
    }
}
