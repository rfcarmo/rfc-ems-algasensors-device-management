package com.algaworks.algasensors.devicemanagement;

import io.hypersistence.tsid.TSID;

import java.util.Optional;

public class IdGenerator {

    private static final TSID.Factory tsidFactory;

    static {
        Optional.ofNullable(System.getenv("tsid.node")).ifPresent(tsidNode -> System.setProperty("tsid.node", "2"));
        Optional.ofNullable(System.getenv("tsid.node.count")).ifPresent(tsidNodeCount -> System.setProperty("tsid.node.count", "32"));

        tsidFactory = TSID.Factory.builder().build();
    }

    private IdGenerator() {
    }

    public static TSID generateTSID() {
        return tsidFactory.generate();
    }

}
