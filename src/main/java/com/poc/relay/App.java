package com.poc.relay;

import com.poc.relay.helper.DepartureTimeComparator;
import com.poc.relay.models.Pilot;
import com.poc.relay.models.PitStop;
import com.poc.relay.models.Schedule;
import com.poc.relay.processor.ComputeWaitTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Hello world!
 *
 */
public class App 
{

    static int INF = Integer.MAX_VALUE;
    static final int V = 3;

    //Initialize pitStop queues for Left and Right directions
    static PitStop pitStopA = new PitStop("C", "B", "A");
    static PitStop pitStopB = new PitStop("A", "C", "B");
    static PitStop pitStopC = new PitStop("B", "A", "C");

    static ArrayList<PitStop> pitStops = new ArrayList<PitStop>();

    static int graph[][] = new int[][]
            {
                    {0, 1, INF},
                    {INF, 0, 1},
                    {INF, INF, 0}
            };


    static Schedule schedule1 = new Schedule(pitStopA, pitStopC, pitStopA, 111, 22, 48);
    static Schedule schedule2 = new Schedule(pitStopB, pitStopA, pitStopB, 112, 16, 48);

    static Pilot pilot1 = new Pilot(111, 222, 0, 0);
    static Pilot pilot2 = new Pilot(222, 111, 0, 0);

    public static void main( String[] args )
    {

        //Add only first trip segment information of all trips into schedulePriorityQueue
        PriorityBlockingQueue<Schedule> schedulePriorityQueue = new PriorityBlockingQueue<Schedule>(100,
                new DepartureTimeComparator());
        schedulePriorityQueue.add(schedule1);
        schedulePriorityQueue.add(schedule2);


        //Initialize pilots to pitStops
        pitStopA.getLeftPitStopQueue().add(pilot1);
        pitStopA.getRightPitStopQueue().add(pilot2);
        pitStops.addAll(Arrays.asList(pitStopA, pitStopB, pitStopC));

        System.out.println( "Hello Nav!" );
        ComputeWaitTime computeWaitTime = new ComputeWaitTime();
        computeWaitTime.getMinimumWaitTime(graph, V, schedulePriorityQueue, pitStops);
        System.out.println( "Nav Successfully Completed!" );
    }
}
