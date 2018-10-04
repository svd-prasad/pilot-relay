package com.poc.relay.helper;

import com.poc.relay.models.Schedule;

import java.util.Comparator;

/**
 * @author svdprasad
 */
public class DepartureTimeComparator implements Comparator<Schedule> {

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