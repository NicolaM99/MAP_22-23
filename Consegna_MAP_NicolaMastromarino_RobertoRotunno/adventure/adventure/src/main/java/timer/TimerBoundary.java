package timer;

public abstract class TimerBoundary {

    protected abstract long getMaxTime();

    protected abstract long getCurrentTimeSecond();

    protected abstract long getMaxTimeSecond();

    protected abstract long getMinutePassed(long currentTimeSeconds);

    protected abstract long getSecondPassed(long currentTimeSeconds);

    /**
     * Method to start the timer
     */
    protected final void startTimerMessage() {
        System.out.println("Il timer e' di " + getMaxTime() + " minuti.");
    }

    /**
     * Method to end the timer
     */
    protected final void endTimerMessage() {
        System.out.println("Il tempo a tua disposizione e' scaduto. Sei morto!");
    }

    public void printCurrentTime() {
        long minutesPassed = getMinutePassed(getCurrentTimeSecond());
        long secondsPassed = getSecondPassed(getCurrentTimeSecond());
        if (minutesPassed == 0) {
            System.out.println("Sono passati " + secondsPassed + " secondi.");
        } else if (minutesPassed == 1) {
            System.out.println("E' passato " + minutesPassed + " minuto e " + secondsPassed + " secondi.");
        } else if (minutesPassed > 1) {
            System.out.println("Sono passati " + minutesPassed + " minuti e " + secondsPassed + " secondi.");
        }
    }

}
