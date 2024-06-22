/**
 * @(#)FileChooserTest.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch22.selectionofcmp;

import static net.will.javatest.thkij4ed.ch22.SwingConsole.run;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * <p>
 * Demonstration of File dialog boxes.
 * </p>
 * 
 * <p>
 * For an "open file" dialog, you call showOpenDialog( ), and for a "save file"
 * dialog, you call showSaveDialog( ). These commands don’t return until the
 * dialog is closed. The JFileChooser object still exists, so you can read data
 * from it. The methods getSelectedFile( ) and getCurrentDirectory( ) are two
 * ways you can interrogate the results of the operation. If these return null,
 * it means the user canceled out of the dialog.
 * </p>
 * 
 * @author Will
 * @version 2012-4-12
 */
public class FileChooserTest extends JFrame {
	private static final long serialVersionUID = 2164573280516927489L;

	private JTextField fileName = new JTextField(), dir = new JTextField();
	private JButton open = new JButton("Open"), save = new JButton("Save");

	public FileChooserTest() {
		JPanel p = new JPanel();
		open.addActionListener(new OpenL());
		p.add(open);
		save.addActionListener(new SaveL());
		p.add(save);
		add(p, BorderLayout.SOUTH);
		dir.setEditable(false);
		fileName.setEditable(false);
		p = new JPanel();
		p.setLayout(new GridLayout(2, 1));
		p.add(fileName);
		p.add(dir);
		add(p, BorderLayout.NORTH);
	}

	class OpenL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser c = new JFileChooser();
			// Demonstrate "Open" dialog:
			int rVal = c.showOpenDialog(FileChooserTest.this);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				fileName.setText(c.getSelectedFile().getName());
				dir.setText(c.getCurrentDirectory().toString());
			}
			if (rVal == JFileChooser.CANCEL_OPTION) {
				fileName.setText("You pressed cancel");
				dir.setText("");
			}
		}
	}

	class SaveL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser c = new JFileChooser();
			// Demonstrate "Save" dialog:
			int rVal = c.showSaveDialog(FileChooserTest.this);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				fileName.setText(c.getSelectedFile().getName());
				dir.setText(c.getCurrentDirectory().toString());
			}
			if (rVal == JFileChooser.CANCEL_OPTION) {
				fileName.setText("You pressed cancel");
				dir.setText("");
			}
		}
	}

	public static void main(String[] args) {
		run(new FileChooserTest(), 250, 150);
	}

}
