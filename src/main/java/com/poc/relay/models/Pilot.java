package com.poc.relay.models;

/**
 * @author svdprasad
 * Pilot – A Pilot is defined by firstTripId, secondTripId, arrivalTimeAtWaitingPitstop, departureTimeAtWaitingPitstop
 */
public class Pilot {

    int firstTripId;
    int secondTripId;
    double arrivalTimeAtWaitingPitstop;
    double departureTimeAtWaitingPitstop;

    public Pilot(int firstTripId, int secondTripId, double arrivalTimeAtWaitingPitstop, double departureTimeAtWaitingPitstop) {
        this.firstTripId = firstTripId;
        this.secondTripId = secondTripId;
        this.arrivalTimeAtWaitingPitstop = arrivalTimeAtWaitingPitstop;
        this.departureTimeAtWaitingPitstop = departureTimeAtWaitingPitstop;
    }

    @Override
    public String toString() {
        return "Pilot{" +
                "firstTripId=" + firstTripId +
                ", secondTripId=" + secondTripId +
                ", arrivalTimeAtWaitingPitstop=" + arrivalTimeAtWaitingPitstop +
                ", departureTimeAtWaitingPitstop=" + departureTimeAtWaitingPitstop +
                '}';
    }
}
