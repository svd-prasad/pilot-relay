package com.poc.relay.processor;

import com.poc.relay.helper.DepartureTimeComparator;
import com.poc.relay.helper.FloydWarshell;
import com.poc.relay.models.Pilot;
import com.poc.relay.models.PitStop;
import com.poc.relay.models.Schedule;

import java.util.PriorityQueue;

/**
 * @author svdprasad
 */
public class ComputeWaitTime {

    final int V = 4;
    int INF = Integer.MAX_VALUE;
    int dist[][] = new int[V][V];

    //Initialize pitStop queues for Left and Right directions
    PitStop pitStopA = new PitStop("C", "B", "A");
    PitStop pitStopB = new PitStop("A", "C", "B");
    PitStop pitStopC = new PitStop("B", "A", "C");


    int graph[][] = new int[][]
            {
                    {0, INF, -2, INF},
                    {4, 0, 3, INF},
                    {INF, INF, 0, 2},
                    {INF, -1, INF, 0}
            };


    Schedule schedule1 = new Schedule(pitStopA, pitStopC, pitStopA, 111, 22, 48);
    Schedule schedule2 = new Schedule(pitStopB, pitStopA, pitStopB, 112, 16, 48);

    Pilot pilot1 = new Pilot(111, 222, 0, 0);
    Pilot pilot2 = new Pilot(222, 111, 0, 0);


    public void getMinimumWaitTime() {

        //Add only first trip segment information of all trips into schedulePriorityQueue
        PriorityQueue<Schedule> schedulePriorityQueue = new PriorityQueue<Schedule>(100, new DepartureTimeComparator());

        schedulePriorityQueue.add(schedule1);
        schedulePriorityQueue.add(schedule2);

        //Initialize pilots to pitStops
        pitStopA.getLeftPitStopQueue().add(pilot1);
        pitStopA.getRightPitStopQueue().add(pilot2);

        FloydWarshell floydWarshell = new FloydWarshell();
        floydWarshell.floydWarshell(graph, V);

        for (Schedule schedule : schedulePriorityQueue) {
            System.out.println("nav " + schedule);
        }

    }

    public void print() {
        System.out.println(schedule1.toString());
    }

}
