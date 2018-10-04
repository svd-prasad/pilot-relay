package com.poc.relay;

import com.poc.relay.processor.ComputeWaitTime;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello Nav!" );
        ComputeWaitTime computeWaitTime = new ComputeWaitTime();
        computeWaitTime.getMinimumWaitTime();
        System.out.println( "Nav Successfully Completed!" );
    }
}
