package com.poc.relay.models;

import lombok.Data;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author svdprasad
 */
@Data
public class PitStop {

    String leftPitStop;
    String rightPitStop;
    String pitStop;
    Queue<Pilot> leftPitStopQueue;
    Queue<Pilot> rightPitStopQueue;


    public PitStop(String leftPitStop, String rightPitStop, String pitStop) {
        this.leftPitStop = leftPitStop;
        this.rightPitStop = rightPitStop;
        this.pitStop = pitStop;
        this.leftPitStopQueue = new LinkedList<Pilot>();
        this.rightPitStopQueue = new LinkedList<Pilot>();
    }

}
