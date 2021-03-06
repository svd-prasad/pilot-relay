package com.poc.relay.processor;

import com.poc.relay.helper.FloydWarshell;
import com.poc.relay.models.PitStop;
import com.poc.relay.models.Schedule;

import java.util.ArrayList;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author svdprasad
 */
public class ComputeWaitTime {

    public void getMinimumWaitTime(int pitStopConnectivityMatrix[][], int V, PriorityBlockingQueue<Schedule> schedulePriorityQueue,
                                   ArrayList<PitStop> pitStops) {

        FloydWarshell floydWarshell = new FloydWarshell();
        final int shortestPath[][] = floydWarshell.getAllPairsShortestPath(pitStopConnectivityMatrix, V);

        schedulePriorityQueue.stream().forEach((Schedule schedule) -> {
            System.out.println("Processing nav schedule " + schedule);
            schedulePriorityQueue.remove(schedule);

            int curPitStopNode = pitStops.indexOf(schedule.getCurrentNode().getPitStop().trim());
            int endPitStopNode = pitStops.indexOf(schedule.getEndNode().getPitStop().trim());
            int nextPitStopNodeId = floydWarshell.findNextPitStopTowardEndNode(shortestPath, V, curPitStopNode, endPitStopNode);

            System.out.println("[Nav] Next Nearest PitStop " + nextPitStopNodeId);

            if (nextPitStopNodeId != -1) {
                String nextPitStop = pitStops.get(nextPitStopNodeId).getPitStop();
                for (int i = 0; i < pitStops.size(); i++) {
                    if (pitStops.get(i).getPitStop().equalsIgnoreCase(nextPitStop)) {
                        Schedule nextSchedule = new Schedule(schedule.getStartNode(), schedule.getEndNode(),
                                pitStops.get(i), 111, 22, 48);
                        schedulePriorityQueue.add(nextSchedule);
                    }
                }
            }
            System.out.println("Processed nav schedule " + schedule);
        });

    }

}
