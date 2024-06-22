/**
 * @(#)ButtonGroups.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch22.selectionofcmp;

import static net.will.javatest.thkij4ed.ch22.SwingConsole.run;

import java.awt.FlowLayout;
import java.lang.reflect.Constructor;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

/**
 * Uses reflection to create groups of different types of AbstractButton.
 *
 * @author Will
 * @version 2012-4-10
 */
public class ButtonGroups extends JFrame {
	private static final long serialVersionUID = 3359733637914922457L;

	private static String[] ids = { "June", "Ward", "Beaver", "Wally", "Eddie",
			"Lumpy" };

	@SuppressWarnings("rawtypes")
	static JPanel makeBPanel(Class<? extends AbstractButton> kind, String[] ids) {
		ButtonGroup bg = new ButtonGroup();
		JPanel jp = new JPanel();
		String title = kind.getName();
		title = title.substring(title.lastIndexOf(".") + 1);
		jp.setBorder(new TitledBorder(title));
		for (String id : ids) {
			AbstractButton ab = new JButton("failed");
			try {
				// Get the dynamic constructor method
				// that takes a String argument:
				Constructor ctor = kind.getConstructor(String.class);
				// Create a new object:
				ab = (AbstractButton) ctor.newInstance(id);
			} catch (Exception ex) {
				System.err.println("can’t create " + kind);
			}
			bg.add(ab);
			jp.add(ab);
		}
		return jp;
	}

	public ButtonGroups() {
		setLayout(new FlowLayout());
		add(makeBPanel(JButton.class, ids));
		add(makeBPanel(JToggleButton.class, ids));
		add(makeBPanel(JCheckBox.class, ids));
		add(makeBPanel(JRadioButton.class, ids));
	}

	public static void main(String[] args) {
		run(new ButtonGroups(), 500, 350);
	}

}
