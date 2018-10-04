package com.poc.relay.processor;

import com.poc.relay.models.Pilot;
import com.poc.relay.models.PitStop;
import com.poc.relay.models.Schedule;

import java.sql.Driver;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author svdprasad
 */
public class ComputeWaitTime {

    final static int INF = 99999, V = 3;
    int dist[][] = new int[V][V];

    //Initialize pitStop queues for Left and Right directions
    PitStop pitStopA = new PitStop("C", "B", "A");
    PitStop pitStopB = new PitStop("A", "C", "B");
    PitStop pitStopC = new PitStop("B", "A", "C");


    int graph[][] = { {0, 1,  1},
            {1, 0, 1},
            {1, 1, 0}
    };


    Schedule schedule1 = new Schedule(pitStopA, pitStopC, pitStopA,111, 22, 48);
    Schedule schedule2 = new Schedule(pitStopB, pitStopA, pitStopB,112, 16, 48);

    Pilot pilot1 = new Pilot(111,222, 0,0);
    Pilot pilot2 = new Pilot(222,111, 0,0);



    public void getMinimumWaitTime() {

        //Add only first trip segment information of all trips into schedulePriorityQueue
        PriorityQueue<Schedule> schedulePriorityQueue=new PriorityQueue<Schedule>(100, new DepartureTimeComparator());

        schedulePriorityQueue.add(schedule1);
        schedulePriorityQueue.add(schedule2);

        //Initialize pilots to pitStops
        pitStopA.getLeftPitStopQueue().add(pilot1);
        pitStopA.getRightPitStopQueue().add(pilot2);



        AllPairShortestPath a = new AllPairShortestPath();
        a.floydWarshall(graph);

        for(Schedule schedule: schedulePriorityQueue) {

            System.out.println("nav "+ schedule);


        }








    }

   static class DepartureTimeComparator implements Comparator<Schedule> {

        // Overriding compare() method of Comparator
        // for descending order of StartByFromCurrentNode
        public int compare(Schedule s1, Schedule s2) {
            if (s1.getStartByFromCurrentNode() > s2.getStartByFromCurrentNode())
                return 1;
            else if (s1.getStartByFromCurrentNode() < s2.getStartByFromCurrentNode())
                return -1;
            return 0;
        }

    }


    class AllPairShortestPath
    {


        void floydWarshall(int graph[][])
        {

            int i, j, k;

        /* Initialize the solution matrix same as input graph matrix.
           Or we can say the initial values of shortest distances
           are based on shortest paths considering no intermediate
           vertex. */
            for (i = 0; i < V; i++)
                for (j = 0; j < V; j++)
                    dist[i][j] = graph[i][j];

        /* Add all vertices one by one to the set of intermediate
           vertices.
          ---> Before start of an iteration, we have shortest
               distances between all pairs of vertices such that
               the shortest distances consider only the vertices in
               set {0, 1, 2, .. k-1} as intermediate vertices.
          ----> After the end of an iteration, vertex no. k is added
                to the set of intermediate vertices and the set
                becomes {0, 1, 2, .. k} */
            for (k = 0; k < V; k++)
            {
                // Pick all vertices as source one by one
                for (i = 0; i < V; i++)
                {
                    // Pick all vertices as destination for the
                    // above picked source
                    for (j = 0; j < V; j++)
                    {
                        // If vertex k is on the shortest path from
                        // i to j, then update the value of dist[i][j]
                        if (dist[i][k] + dist[k][j] < dist[i][j])
                            dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }

            // Print the shortest distance matrix
            printSolution(dist);
        }

        void printSolution(int dist[][])
        {
            System.out.println("The following matrix shows the shortest "+
                    "distances between every pair of vertices");
            for (int i=0; i<V; ++i)
            {
                for (int j=0; j<V; ++j)
                {
                    if (dist[i][j]==INF)
                        System.out.print("INF ");
                    else
                        System.out.print(dist[i][j]+"   ");
                }
                System.out.println();
            }
        }

    }
    public void print() {
        System.out.println(schedule1.toString());
    }

}
