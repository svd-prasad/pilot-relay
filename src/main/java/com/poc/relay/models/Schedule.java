package com.poc.relay.models;

/**
 * @author svdprasad
 * Schedule – A schedule is defined by startNode, endNode, currentNode, tripId, startBy_FromCurrentNode, arriveBy_AtEndNode
 */
public class Schedule {

    PitStop startNode;
    PitStop endNode;
    PitStop currentNode;
    int tripId;
    int startByFromCurrentNode;
    int arriveByAtEndNode;

    public Schedule(PitStop startNode, PitStop endNode, PitStop currentNode, int tripId, int startByFromCurrentNode, int arriveByAtEndNode) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.currentNode = currentNode;
        this.tripId = tripId;
        this.startByFromCurrentNode = startByFromCurrentNode;
        this.arriveByAtEndNode = arriveByAtEndNode;
    }

    public PitStop getStartNode() {
        return startNode;
    }

    public void setStartNode(PitStop startNode) {
        this.startNode = startNode;
    }

    public PitStop getEndNode() {
        return endNode;
    }

    public void setEndNode(PitStop endNode) {
        this.endNode = endNode;
    }

    public PitStop getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(PitStop currentNode) {
        this.currentNode = currentNode;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public int getStartByFromCurrentNode() {
        return startByFromCurrentNode;
    }

    public void setStartByFromCurrentNode(int startByFromCurrentNode) {
        this.startByFromCurrentNode = startByFromCurrentNode;
    }

    public int getArriveByAtEndNode() {
        return arriveByAtEndNode;
    }

    public void setArriveByAtEndNode(int arriveByAtEndNode) {
        this.arriveByAtEndNode = arriveByAtEndNode;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "startNode='" + startNode + '\'' +
                ", endNode='" + endNode + '\'' +
                ", currentNode='" + currentNode + '\'' +
                ", tripId=" + tripId +
                ", startByFromCurrentNode=" + startByFromCurrentNode +
                ", arriveByAtEndNode=" + arriveByAtEndNode +
                '}';
    }

}
