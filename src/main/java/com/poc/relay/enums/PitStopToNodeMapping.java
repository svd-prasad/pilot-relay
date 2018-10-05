package com.poc.relay.enums;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author svdprasad
 */
public enum PitStopToNodeMapping {

    A(0),
    B(1),
    C(2),
    UNKNOWN(0);

    private int getPitStopToNode;


    PitStopToNodeMapping(int getPitStopToNode) {
        this.getPitStopToNode = getPitStopToNode;
    }

    public int getPitStopToNode() {
        return getPitStopToNode;
    }

    public static PitStopToNodeMapping[] pitStopToNodeMappings = PitStopToNodeMapping.values();

}
