/**
 * @(#)SWTConsole.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch22.swt;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 * 
 * @author Will
 * @version 2012-4-20
 */
public class SWTConsole {
	public static void run(SWTApplication swtApp, int width, int height) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText(swtApp.getClass().getSimpleName());
		swtApp.createContents(shell);
		shell.setSize(width, height);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}
