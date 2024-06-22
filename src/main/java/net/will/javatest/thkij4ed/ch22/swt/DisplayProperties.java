/**
 * @(#)DisplayProperties.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch22.swt;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * 
 * 
 * @author Will
 * @version 2012-4-19
 */
public class DisplayProperties {
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Display Properties");
		shell.setLayout(new FillLayout());
		Text text = new Text(shell, SWT.WRAP | SWT.V_SCROLL);
		StringWriter props = new StringWriter();
		System.getProperties().list(new PrintWriter(props));
		text.setText(props.toString());
		shell.open();
		while (!shell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
		display.dispose();
	}
}
