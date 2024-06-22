/**
 * @(#)InterruptableLongRunningCallable.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch22.concurrency;

import static net.will.javatest.thkij4ed.ch22.SwingConsole.run;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Callable;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Using Callables for long-running tasks.
 * 
 * @author Will
 * @version 2012-4-13
 */
public class InterruptableLongRunningCallable extends JFrame {
	private static final long serialVersionUID = 7187176419104964206L;

	private JButton b1 = new JButton("Start Long Running Task"),
			b2 = new JButton("End Long Running Task"),
			b3 = new JButton("Get results");
	private TaskManager<String, CallableTask> manager = new TaskManager<String, CallableTask>();

	public InterruptableLongRunningCallable() {
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CallableTask task = new CallableTask();
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
				// Sample call to a Task method:
				for (TaskItem<String, CallableTask> tt : manager)
					tt.task.id(); // No cast required
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
		run(new InterruptableLongRunningCallable(), 200, 150);
	}
}

class CallableTask extends Task implements Callable<String> {
	public String call() {
		run();
		return "Return value of " + this;
	}
}
