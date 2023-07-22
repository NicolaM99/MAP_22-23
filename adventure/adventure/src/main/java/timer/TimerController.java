package timer;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


/**
 * @author Nicola
 * @author Roberto
 */
public class TimerController extends TimerBoundary {
    private static final TimerController CONTROLLER = new TimerController();
    private static final long DEFAULT_TIME = 1;
    private static final int SECONDS_IN_MINUTE = 60;
    private long maxTime = TimeUnit.MILLISECONDS.convert(DEFAULT_TIME, TimeUnit.MINUTES);
    private Timer timer = new Timer();
    private boolean isRunning;
    private long startTime;


    /**
     * Ritorna l'istanza della classe TimerController.
     */
    public static TimerController getInstance() {
        return CONTROLLER;
    }

    private void setStartTime(final long valStartTime) {
        this.startTime = valStartTime;
    }

    /**
     * Checks if the timer is currently running.
     *
     * @return true if the timer is running, false otherwise
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Sets the running state of the timer.
     *
     * @param valIsRunning true to set the timer as running, false otherwise
     */
    public void setRunning(final boolean valIsRunning) {
        this.isRunning = valIsRunning;
    }

    /**
     * Starts the game timer.
     */
    public void startGame() {
        this.timer = new Timer();
        setStartTime(System.currentTimeMillis());

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                stopTimer();
            }
        };

        timer.schedule(task, maxTime);
        startTimerMessage();
        setRunning(true);
    }

    /**
     * Stops the game timer if it is running.
     */
    public void stopTimer() {
        if (isRunning) {
            //endTimerMessage();
            setRunning(false);
        }
        timer.cancel();
        // if che viene eseguito solo se il timer è scaduto
        // il numero 50 sta per i millisecondi di tolleranza

        if ((getCurrentTimeMillis() - maxTime) < 50 && (getCurrentTimeMillis() - maxTime) > -50) {
            endTimerMessage();
            System.exit(0);
        }
    }

    /**
     * Sets the maximum time in minutes for the game timer.
     *
     * @param minutes the maximum time in minutes
     */
    public void setMaxTime(final int minutes) {
        if (minutes < 0) {
            throw new IllegalArgumentException("The maximum time cannot be negative.");
        }
        this.maxTime = TimeUnit.MILLISECONDS.convert(minutes, TimeUnit.MINUTES);
    }

    /*public void addMinutes(int timeToAdd) {

        long remainingTime = maxTime - getCurrentTimeMillis();
        setMaxTime((int) remainingTime);
        //cancello il vecchio timer
        if (isRunning) {
            setRunning(false);
        }
        timer.cancel();

        this.timer = new Timer();
        setStartTime(System.currentTimeMillis());

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (isRunning) {
                    setRunning(false);
                }
                timer.cancel();
                endTimerMessage();
                System.exit(0);
            }
        };

        System.out.println("\n\nnuovo tempo"+(remainingTime + TimeUnit.MILLISECONDS.convert(timeToAdd, TimeUnit.MINUTES)));
        timer.schedule(task, (remainingTime + TimeUnit.MILLISECONDS.convert(timeToAdd, TimeUnit.MINUTES)));
        startTimerMessage();
        setRunning(true);

    } */

    public void addMinutes(int timeToAdd) {
        this.maxTime += TimeUnit.MILLISECONDS.convert(timeToAdd, TimeUnit.MINUTES);
        long remainingTime = maxTime - getCurrentTimeMillis();
        maxTime = remainingTime;
        if (isRunning) {
            setRunning(false);
        }
        timer.cancel();

        this.timer = new Timer();
        setStartTime(System.currentTimeMillis());

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (isRunning) {
                    setRunning(false);
                }
                timer.cancel();
                endTimerMessage();
                System.exit(0);
            }
        };

        timer.schedule(task, remainingTime);

        setRunning(true);
    }


    /**
     * Returns the current time in milliseconds elapsed since the start time.
     *
     * @return the current time in milliseconds
     */
    long getCurrentTimeMillis() {
        return System.currentTimeMillis() - startTime;
    }

    private long milliToSecond(final long milliseconds) {
        return TimeUnit.SECONDS.convert(milliseconds, TimeUnit.MILLISECONDS);
    }

    /**
     * Returns the maximum time in minutes for the game timer.
     *
     * @return the maximum time in minutes
     */
    @Override
    protected long getMaxTime() {
        return TimeUnit.MINUTES.convert(maxTime, TimeUnit.MILLISECONDS);
    }

    private long getMaxTimeMills() {
        return maxTime;
    }

    /**
     * Returns the current time in seconds.
     *
     * @return the current time in seconds
     */
    @Override
    protected long getCurrentTimeSecond() {
        return milliToSecond(getCurrentTimeMillis());
    }

    /**
     * Returns the maximum time in seconds.
     *
     * @return the maximum time in seconds
     */
    @Override
    protected long getMaxTimeSecond() {
        return milliToSecond(maxTime);
    }

    /**
     * Calculates the number of minutes passed based on the given current time in
     * seconds.
     *
     * @param currentTimeSeconds the current time in seconds
     * @return the number of minutes passed
     */
    @Override
    protected long getMinutePassed(final long currentTimeSeconds) {
        return currentTimeSeconds / SECONDS_IN_MINUTE;
    }

    /**
     * Calculates the number of seconds passed based on the given current time in
     * seconds.
     *
     * @param currentTimeSeconds the current time in seconds
     * @return the number of seconds passed
     */
    @Override
    protected long getSecondPassed(final long currentTimeSeconds) {
        return currentTimeSeconds % SECONDS_IN_MINUTE;
    }

    /*public void printRemainingTime() {
        long millisecondsRemaining = getMaxTimeMills() - TimerController.getInstance().getCurrentTimeMillis();
        System.out.println("\n\ngetMaxTime(): "+getMaxTimeMills()+"\ngetCurrentTimeMillis()"+TimerController.getInstance().getCurrentTimeMillis());
        long minutesRemaining = TimeUnit.MILLISECONDS.toMinutes(millisecondsRemaining);
        long secondsRemaining = TimeUnit.MILLISECONDS.toSeconds(millisecondsRemaining) % 60;

        if (minutesRemaining == 0) {
            System.out.println("Ti rimangono " + secondsRemaining + " secondi.");
        } else if (minutesRemaining == 1) {
            System.out.println("Ti rimane " + minutesRemaining + " minuto e " + secondsRemaining + " secondi.");
        } else {
            System.out.println("Ti rimangono " + minutesRemaining + " minuti e " + secondsRemaining + " secondi.");
        }
    }*/


    /*public void printRemainingTime() {
        /*long millisecondsRemaining = getMaxTimeMills() - TimerController.getInstance().getCurrentTimeMillis();
        long minutesRemaining = TimeUnit.MILLISECONDS.toMinutes(millisecondsRemaining);
        long secondsRemaining = TimeUnit.MILLISECONDS.toSeconds(millisecondsRemaining) % 60;


        long remainingTime = getMaxTimeMills() - getCurrentTimeMillis();
        long minutesRemaining=TimeUnit.MILLISECONDS.toMinutes(remainingTime);
        long secondsRemaining=TimeUnit.MILLISECONDS.toSeconds(remainingTime) % 60;

        if (minutesRemaining == 0) {
            System.out.println("Ti rimangono " + secondsRemaining + " secondi.");
        } else if (minutesRemaining == 1) {
            System.out.println("Ti rimane " + minutesRemaining + " minuto e " + secondsRemaining + " secondi.");
        } else {
            System.out.println("Ti rimangono " + minutesRemaining + " minuti e " + secondsRemaining + " secondi.");
        }
    }*/


    public void printRemainingTime() {
        long remainingTime = getMaxTimeMills() - getCurrentTimeMillis();
        System.out.println("Ti rimangono: " + TimeUnit.MILLISECONDS.toMinutes(remainingTime) + " minuti e " + TimeUnit.MILLISECONDS.toSeconds(remainingTime) % 60 + " secondi.");
    }


}

