import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Potato on 5/26/2014.
 */

//TIMER THAT INCREASES COUNT BY 1 EVERY SECOND
public class newTimer2 {
    public static Timer tim;
    public static int count=0;


    static int secs;
    public newTimer2(int sec){
        secs=sec;
        tim=new Timer(sec*1000,new TimerListener());
        tim.start();

    }


    private class TimerListener implements ActionListener {
        public void actionPerformed (ActionEvent e)
        {
            count++;


        }


    }



}
