package fil.coo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class TimerTest {

	public static void main( String[] args )    {
		int delay = 1000;
		ActionListener task = new ActionListenerTest();
		Timer timer = new Timer(delay, task);
		timer.start();

		while (true);
    }

	public static class ActionListenerTest implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			System.out.println(java.util.Calendar.getInstance().getTime());
		}
		
	}

}
