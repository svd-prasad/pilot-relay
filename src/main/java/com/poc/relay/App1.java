package com.poc.relay;

import com.opencsv.CSVReader;
import java.io.FileReader;

/**
 * @author svdprasad
 */
public class App1 {
    private static final String CSV_FILE_PATH
            = "src/main/resources/config.csv";
    public static void readDataLineByLine(String file)
    {

        try {

            // Create an object of filereader class
            // with CSV file as a parameter.
            FileReader filereader = new FileReader(file);

            // create csvReader object passing
            // filereader as parameter
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;

            // we are going to read data line by line
            while ((nextRecord = csvReader.readNext()) != null) {
                for (String cell : nextRecord) {
                    System.out.print(cell + "\t");
                }
                System.out.println();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println( "Hello Nav!" );
        readDataLineByLine(CSV_FILE_PATH);
        System.out.println( "Nav Successfully Completed!" );
    }
}
