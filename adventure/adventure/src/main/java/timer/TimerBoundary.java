package timer;

public abstract class TimerBoundary {

    protected abstract long getMaxTime();

    protected abstract long getCurrentTimeSecond();

    protected abstract long getMaxTimeSecond();

    protected abstract long getMinutePassed(long currentTimeSeconds);

    protected abstract long getSecondPassed(long currentTimeSeconds);

    protected final void startTimerMessage() {
        System.out.println("Il timer è di " + getMaxTime() + " minuti.");
    }

    protected final void endTimerMessage() {
        System.out.println("Il tempo a tua disposizione è scaduto. sei morto!");
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


    // metodo per stampare il tempo rimanente
    // deve convertire i millisecondi in minuti e secondi
    // e stampare il tempo rimanente
    /*public void printRemainingTime() {
        long minutesRemaining = getMinutePassed(getMaxTimeSecond() - getCurrentTimeSecond());
        long secondsRemaining = getSecondPassed(getMaxTimeSecond() - getCurrentTimeSecond());
        if (minutesRemaining == 0) {
            System.out.println("Ti rimangono " + secondsRemaining + " secondi.");
        } else if (minutesRemaining == 1) {
            System.out.println("Ti rimane " + minutesRemaining + " minuto e " + secondsRemaining + " secondi.");
        } else if (minutesRemaining > 1) {
            System.out.println("Ti rimangono " + minutesRemaining + " minuti e " + secondsRemaining + " secondi.");
        }
    } */



}
