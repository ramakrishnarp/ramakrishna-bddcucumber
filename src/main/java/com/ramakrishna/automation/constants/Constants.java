package com.ramakrishna.automation.constants;

public interface Constants {

    /**
     * 250 milli seconds delay.
     */
    int WAIT_FOR_250MS = 250;
    /**
     * 500 milli seconds delay.
     */
    int WAIT_FOR_500MS = 500;

    /**
     * 1000 milli seconds delay.
     */
    int WAIT_FOR_1000MS = 1000;

    /**
     * 2000 milli seconds delay.
     */
    int WAIT_FOR_2000MS = 2000;

    /**
     * 4000 milli seconds delay.
     */
    int WAIT_FOR_4000MS = 4000;

    /**
     * 7000 milli seconds delay.
     */
    int WAIT_FOR_7000MS = 7000;

    /**
     * This method is an enumeration of the Waiting/delay time.
     * 
     *
     */
    public enum WaitingTime {
        /**
         * quarter second delay.
         */
        QUARTER_SECOND,
        /**
         * half second delay.
         */
        HALF_SECOND,
        /**
         * 1 second delay.
         */
        SECOND,
        /**
         * 2 seconds delay.
         */
        SHORT,
        /**
         * 4 seconds delay.
         */
        MEDIUM,
        /**
         * 7 seconds delay.
         */
        LONG,
    }

}