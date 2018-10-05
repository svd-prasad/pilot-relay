package com.poc.relay.processor;

import com.poc.relay.enums.PitStopToNodeMapping;
import com.poc.relay.helper.DepartureTimeComparator;
import com.poc.relay.helper.FloydWarshell;
import com.poc.relay.models.Pilot;
import com.poc.relay.models.PitStop;
import com.poc.relay.models.Schedule;

import java.util.Arrays;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.stream.Stream;

/**
 * @author svdprasad
 */
public class ComputeWaitTime {

    final int V = 3;
    int INF = Integer.MAX_VALUE;
    int dist[][] = new int[V][V];
    //Initialize pitStop queues for Left and Right directions
    PitStop pitStopA = new PitStop("C", "B", "A");
    PitStop pitStopB = new PitStop("A", "C", "B");
    PitStop pitStopC = new PitStop("B", "A", "C");
    int graph[][] = new int[][]
            {
                    {0, 1, INF},
                    {INF, 0, 1},
                    {INF, INF, 0}
            };
    Schedule schedule1 = new Schedule(pitStopA, pitStopC, pitStopA, 111, 22, 48);
    Schedule schedule2 = new Schedule(pitStopB, pitStopA, pitStopB, 112, 16, 48);
    Pilot pilot1 = new Pilot(111, 222, 0, 0);
    Pilot pilot2 = new Pilot(222, 111, 0, 0);
    private PitStopToNodeMapping[] pitStopToNodeMappings = PitStopToNodeMapping.values();

    private int printPath(int[][] path, int v, int u) {

        if (path[v][u] == v)
            return v;
        printPath(path, v, path[v][u]);
        return path[v][u];
        //System.out.print(path[v][u] + " ");

    }

    private int findNextPitStopTowardEndNode( int[][] path, int N, int v, int u) {

        int nextPitStop=-1;
        if (u != v && path[v][u] != -1) {
          //  System.out.print("Shortest Path from vertex " + v +
            //        " to vertex " + u + " is (" + v + " ");
            nextPitStop=printPath(path, v, u);
            //System.out.println(u + ")");
        }
        return nextPitStop;
    }

    public void getMinimumWaitTime() {

        //Add only first trip segment information of all trips into schedulePriorityQueue
        PriorityBlockingQueue<Schedule> schedulePriorityQueue = new PriorityBlockingQueue<Schedule>(100, new DepartureTimeComparator());

        schedulePriorityQueue.add(schedule1);
        schedulePriorityQueue.add(schedule2);

        //Initialize pilots to pitStops
        pitStopA.getLeftPitStopQueue().add(pilot1);
        pitStopA.getRightPitStopQueue().add(pilot2);

        FloydWarshell floydWarshell = new FloydWarshell();
        int shortestPath[][] = floydWarshell.floydWarshell(graph, V);

        schedulePriorityQueue.stream().forEach((Schedule schedule) -> {
            System.out.println("Processing nav schedule " + schedule);
            schedulePriorityQueue.remove(schedule);

            int curPitStopNode = PitStopToNodeMapping.valueOf(schedule.getCurrentNode().getPitStop()).getPitStopToNode();
            int endPitStopNode = PitStopToNodeMapping.valueOf(schedule.getEndNode().getPitStop()).getPitStopToNode();
            int nextPitStop=findNextPitStopTowardEndNode(shortestPath, V, curPitStopNode, endPitStopNode);

            System.out.println("[Nav] Next Nearest PitStop " + nextPitStop);

            //get the node to PitStop mapping
            Stream<PitStopToNodeMapping> a1 = Arrays.stream(pitStopToNodeMappings).filter(s -> s.getPitStopToNode() == 0);
            a1.findFirst().get().name();

            schedulePriorityQueue.add( new Schedule(pitStopA, pitStopC, pitStopA, 111,
                    22, 48));




            System.out.println("Processed nav schedule " + schedule);
        });

    }

    public void print() {
        System.out.println(schedule1.toString());
    }

}
