package com.poc.relay.config;

import com.opencsv.CSVReader;
import com.poc.relay.helper.AdjMatrixGraph;
import com.poc.relay.models.PitStop;


import java.io.FileReader;
import java.util.ArrayList;

/**
 * @author svdprasad
 */
public class PitStopConfig {

    private static final String PIT_STOP_FILE_PATH = "src/main/resources/pitstop.csv";
    ArrayList<PitStop> pitStops = new ArrayList<PitStop>();

    public ArrayList<PitStop> initPitStop()
    {
        try {
            FileReader filereader = new FileReader(PIT_STOP_FILE_PATH);
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;

            // we are going to read data line by line
            while ((nextRecord = csvReader.readNext()) != null) {
               pitStops.add(new PitStop( nextRecord[1], nextRecord[2], nextRecord[0]));
            }
            return pitStops;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return pitStops;
    }

    public int getPitStopIndex(String pitStop){
        for (int i = 0; i < pitStops.size(); i++) {
            if(pitStops.get(i).getPitStop().trim().equalsIgnoreCase(pitStop.trim())){
                return i;
            }
        }
        return -1; // Not Found
    }

    public ArrayList<PitStop> getPitStops(){
        return pitStops;
    }

    public AdjMatrixGraph createPitStopAdjMatrix(){
        int V = pitStops.size();
        AdjMatrixGraph adjMatrixGraph = new AdjMatrixGraph(V);
        for (int i = 0; i < pitStops.size(); i++) {
            adjMatrixGraph.addEdge(i, getPitStopIndex(pitStops.get(i).getRightPitStop()));
            adjMatrixGraph.addEdge(getPitStopIndex(pitStops.get(i).getLeftPitStop()), i);
        }
        return adjMatrixGraph;
    }
}
