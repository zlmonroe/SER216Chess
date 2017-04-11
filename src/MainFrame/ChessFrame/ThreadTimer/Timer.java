
package MainFrame.ChessFrame.ThreadTimer;
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
    public void start(){
        if (!isRunning) {
            startTime = System.currentTimeMillis();
            isRunning = true;
        }
    }
    public void pause(){
        if(isRunning) {
            length -= (System.currentTimeMillis() - startTime);
            isRunning = false;
        }
    }
    public double getTime(){
        if(isRunning)
            return((int)(length-(System.currentTimeMillis()-startTime))/10/100.0);
        else
            return (int)(length)/10/100.0;
    }
}
