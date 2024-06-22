/**
 * @(#)DisplayEnvironment.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch22.swt;

import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * 
 *
 * @author Will
 * @version 2012-4-20
 */
public class DisplayEnvironment implements SWTApplication {

	/* (non-Javadoc)
	 * @see net.will.javatest.thkij4ed.ch22.swt.SWTApplication#createContents(org.eclipse.swt.widgets.Composite)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void createContents(Composite parent) {
		parent.setLayout(new FillLayout());
		Text text = new Text(parent, SWT.WRAP | SWT.V_SCROLL);
		for (Map.Entry entry : System.getenv().entrySet()) {
			text.append(entry.getKey() + ": " + entry.getValue() + "\n");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SWTConsole.run(new DisplayEnvironment(), 800, 600);
	}

}
