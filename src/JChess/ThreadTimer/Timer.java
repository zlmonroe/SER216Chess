
package JChess.ThreadTimer;
/**
 * Created by Tim on 4/10/2017.
 */
public class Timer {
    private long length;
    private long startTime;
    private boolean isRunning;

    public Timer(long seconds){
        length = seconds*1000;
        isRunning = false;
    }

    /**
     * Method to start/resume timer countdown. Time remaining must be greater than 0 to start.
     */
    public void start(){
        if (!isRunning && length>0) {
            startTime = System.currentTimeMillis();
            isRunning = true;
        }
    }

    /**
     * Method to pause timer countdown
     */
    public void pause(){
        if(isRunning) {
            length -= (System.currentTimeMillis() - startTime);
            isRunning = false;
        }
    }

    /**
     * Method to return the remaining time on the timer. will automatically stop timer if time would go negative.
     * @return Returns time remaining on the timer, will not go negative.
     */
    public double getTime(){
        double time;
        if(isRunning)
            time = (length-(System.currentTimeMillis()-startTime))/10/100.0;
        else
            time = (length)/10/100.0;
        if(time<=0){
            this.pause();
            length = 0;
            time = 0;
        }
        return time;
    }
}

