package com.poc.relay.processor;

import com.poc.relay.enums.PitStopToNodeMapping;
import com.poc.relay.helper.FloydWarshell;
import com.poc.relay.models.PitStop;
import com.poc.relay.models.Schedule;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author svdprasad
 */
public class ComputeWaitTime {

    public void getMinimumWaitTime(int graph[][], int V, PriorityBlockingQueue<Schedule> schedulePriorityQueue,
                                   ArrayList<PitStop> pitStops) {

        int dist[][] = new int[V][V];
        FloydWarshell floydWarshell = new FloydWarshell();
        int shortestPath[][] = floydWarshell.floydWarshell(graph, V);

        schedulePriorityQueue.stream().forEach((Schedule schedule) -> {
            System.out.println("Processing nav schedule " + schedule);
            schedulePriorityQueue.remove(schedule);

            int curPitStopNode = PitStopToNodeMapping.valueOf(schedule.getCurrentNode().getPitStop()).getPitStopToNode();
            int endPitStopNode = PitStopToNodeMapping.valueOf(schedule.getEndNode().getPitStop()).getPitStopToNode();
            int nextPitStopNodeId = floydWarshell.findNextPitStopTowardEndNode(shortestPath, V, curPitStopNode, endPitStopNode);

            System.out.println("[Nav] Next Nearest PitStop " + nextPitStopNodeId);

            if (nextPitStopNodeId != -1) {
                String nextPitStop = Arrays.stream(PitStopToNodeMapping.pitStopToNodeMappings).filter(s ->
                        s.getPitStopToNode() == nextPitStopNodeId).findFirst().get().name();
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
