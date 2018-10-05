package com.poc.relay.config;

import com.opencsv.CSVReader;
import com.poc.relay.helper.DepartureTimeComparator;
import com.poc.relay.models.PitStop;
import com.poc.relay.models.Schedule;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author svdprasad
 */
public class ScheduleConfig {
    private static final String SCHEDULE_STOP_FILE_PATH = "src/main/resources/schedule.csv";
    PriorityBlockingQueue<Schedule> schedulePriorityQueue = new PriorityBlockingQueue<Schedule>(10,
            new DepartureTimeComparator());

    public PriorityBlockingQueue<Schedule> initScheduleStop(PitStopConfig pitStopConfig)
    {
        try {
            FileReader filereader = new FileReader(SCHEDULE_STOP_FILE_PATH);
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;

            // we are going to read data line by line
            while ((nextRecord = csvReader.readNext()) != null) {
                schedulePriorityQueue.add(new Schedule(pitStopConfig.getPitStops().get(pitStopConfig.getPitStopIndex(nextRecord[0])),
                        pitStopConfig.getPitStops().get(pitStopConfig.getPitStopIndex(nextRecord[1])),
                        pitStopConfig.getPitStops().get(pitStopConfig.getPitStopIndex(nextRecord[2])),
                        Integer.valueOf(nextRecord[3].trim()), Integer.valueOf(nextRecord[4].trim()), Integer.valueOf(nextRecord[5].trim())));
            }
            return schedulePriorityQueue;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return schedulePriorityQueue;
    }
}
