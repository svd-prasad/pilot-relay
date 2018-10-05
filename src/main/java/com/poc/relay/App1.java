package com.poc.relay;

import com.opencsv.CSVReader;
import com.poc.relay.config.PitStopConfig;
import com.poc.relay.config.ScheduleConfig;
import com.poc.relay.processor.ComputeWaitTime;

import java.io.FileReader;

/**
 * @author svdprasad
 */
public class App1 {

    public static void main(String[] args) {
        System.out.println( "Hello Nav!" );

        PitStopConfig pitStopConfig = new PitStopConfig();
        pitStopConfig.initPitStop();
        System.out.println(pitStopConfig.createPitStopAdjMatrix().toString());

        ScheduleConfig scheduleConfig = new ScheduleConfig();
        scheduleConfig.initScheduleStop(pitStopConfig).stream().forEach(s-> System.out.println(s));

        ComputeWaitTime computeWaitTime = new ComputeWaitTime();
        computeWaitTime.getMinimumWaitTime(pitStopConfig.createPitStopAdjMatrix().getAdj(), pitStopConfig.getPitStops().size(),
                scheduleConfig.initScheduleStop(pitStopConfig), pitStopConfig.getPitStops());
        System.out.println( "Nav Successfully Completed!" );
    }

}
