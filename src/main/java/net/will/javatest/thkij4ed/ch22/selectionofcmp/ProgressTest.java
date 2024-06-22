/**
 * @(#)ProgressTest.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch22.selectionofcmp;

import static net.will.javatest.thkij4ed.ch22.SwingConsole.run;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.ProgressMonitor;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * <p>
 * Using sliders, progress bars and progress monitors.
 * </p>
 * 
 * <p>
 * The key to hooking the slider and progress bar components together is in
 * sharing their model, in the line:
 * 
 * <pre>
 * pb.setModel(sb.getModel());
 * </pre>
 * 
 * Of course, you could also control the two using a listener, but using the
 * model is more straightforward for simple situations. The ProgressMonitor does
 * not have a model and so the listener approach is required. Note that the
 * ProgressMonitor only moves forward, and once it reaches the end it closes.
 * The JProgressBar is fairly straightforward, but the JSlider has a lot of
 * options, such as the orientation and major and minor tick marks. Notice how
 * straightforward it is to add a titled border.
 * </p>
 * 
 * @author Will
 * @version 2012-4-12
 */
public class ProgressTest extends JFrame {
	private static final long serialVersionUID = 4763691270921624260L;

	private JProgressBar pb = new JProgressBar();
	private ProgressMonitor pm = new ProgressMonitor(this,
			"Monitoring Progress", "Test", 0, 100);
	private JSlider sb = new JSlider(JSlider.HORIZONTAL, 0, 100, 60);

	public ProgressTest() {
		setLayout(new GridLayout(2, 1));
		add(pb);
		pm.setProgress(0);
		pm.setMillisToPopup(1000);
		sb.setValue(0);
		sb.setPaintTicks(true);
		sb.setMajorTickSpacing(20);
		sb.setMinorTickSpacing(5);
		sb.setBorder(new TitledBorder("Slide Me"));
		pb.setModel(sb.getModel()); // Share model
		add(sb);
		sb.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				pm.setProgress(sb.getValue());
			}
		});
	}

	public static void main(String[] args) {
		run(new ProgressTest(), 300, 200);
	}

}
