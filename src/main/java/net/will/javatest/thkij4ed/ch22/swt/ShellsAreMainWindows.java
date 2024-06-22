/**
 * @(#)ShellsAreMainWindows.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch22.swt;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 * 
 * @author Will
 * @version 2012-4-19
 */
public class ShellsAreMainWindows {

	static Shell[] shells = new Shell[10];

	public static void main(String[] args) {
		Display display = new Display();
		for (int i = 0; i < shells.length; i++) {
			shells[i] = new Shell(display);
			shells[i].setText("Shell #" + i);
			shells[i].open();
		}
		while (!shellsDisposed())
			if (!display.readAndDispatch())
				display.sleep();
		display.dispose();
	}

	static boolean shellsDisposed() {
		for (int i = 0; i < shells.length; i++)
			if (shells[i].isDisposed())
				return true;
		return false;
	}
}
