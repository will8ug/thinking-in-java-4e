/**
 * @(#)HelloSWT.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch22.swt;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * {Requires: org.eclipse.swt.widgets.Display; You must install the SWT library
 * from http://www.eclipse.org }
 * 
 * @author Will
 * @version 2012-4-19
 */
public class HelloSWT {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Hi there, SWT!"); // Title bar
		shell.open();
		while (!shell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
		display.dispose();
	}

}
