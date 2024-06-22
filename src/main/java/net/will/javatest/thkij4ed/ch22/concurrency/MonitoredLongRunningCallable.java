/**
 * @(#)MonitoredLongRunningCallable.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch22.concurrency;

import static net.will.javatest.thkij4ed.ch22.SwingConsole.run;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.ProgressMonitor;
import javax.swing.SwingUtilities;

/**
 * Displaying task progress with ProgressMonitors.
 * 
 * @author Will
 * @version 2012-4-13
 */
public class MonitoredLongRunningCallable extends JFrame {
	private static final long serialVersionUID = 8328624124603921087L;

	private JButton b1 = new JButton("Start Long Running Task"),
			b2 = new JButton("End Long Running Task"),
			b3 = new JButton("Get results");
	private TaskManager<String, MonitoredCallable> manager = new TaskManager<String, MonitoredCallable>();

	public MonitoredLongRunningCallable() {
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MonitoredCallable task = new MonitoredCallable(
						new ProgressMonitor(MonitoredLongRunningCallable.this,
								"Long-Running Task", "", 0, 0));
				manager.add(task);
				System.out.println(task + " added to the queue");
			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (String result : manager.purge())
					System.out.println(result);
			}
		});
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (String result : manager.getResults())
					System.out.println(result);
			}
		});
		setLayout(new FlowLayout());
		add(b1);
		add(b2);
		add(b3);
	}

	public static void main(String[] args) {
		run(new MonitoredLongRunningCallable(), 200, 500);
	}

}

class MonitoredCallable implements Callable<String> {
	private static int counter = 0;
	private final int id = counter++;
	private final ProgressMonitor monitor;
	private final static int MAX = 8;

	public MonitoredCallable(ProgressMonitor monitor) {
		this.monitor = monitor;
		monitor.setNote(toString());
		monitor.setMaximum(MAX - 1);
		monitor.setMillisToPopup(500);
	}

	public String call() {
		System.out.println(this + " started");
		try {
			for (int i = 0; i < MAX; i++) {
				TimeUnit.MILLISECONDS.sleep(500);
				if (monitor.isCanceled())
					Thread.currentThread().interrupt();
				final int progress = i;
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						monitor.setProgress(progress);
					}
				});
			}
		} catch (InterruptedException e) {
			monitor.close();
			System.out.println(this + " interrupted");
			return "Result: " + this + " interrupted";
		}
		System.out.println(this + " completed");
		return "Result: " + this + " completed";
	}

	public String toString() {
		return "Task " + id;
	}
}