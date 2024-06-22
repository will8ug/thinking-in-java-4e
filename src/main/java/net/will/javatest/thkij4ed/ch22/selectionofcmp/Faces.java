/**
 * @(#)Faces.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch22.selectionofcmp;

import static net.will.javatest.thkij4ed.ch22.SwingConsole.run;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Icon behavior in JButtons.
 *
 * @author Will
 * @version 2012-4-11
 */
public class Faces extends JFrame {
	private static final long serialVersionUID = -6559096043911275971L;
	
	private static Icon[] faces;
	private JButton jb, jb2 = new JButton("Disable");
	private boolean mad = false;

	public Faces() {
		faces = new Icon[] {
				new ImageIcon(getClass().getResource("Face0.gif")),
				new ImageIcon(getClass().getResource("Face1.gif")),
				new ImageIcon(getClass().getResource("Face2.gif")),
				new ImageIcon(getClass().getResource("Face3.gif")),
				new ImageIcon(getClass().getResource("Face4.gif")), };
		jb = new JButton("JButton", faces[3]);
		setLayout(new FlowLayout());
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mad) {
					jb.setIcon(faces[3]);
					mad = false;
				} else {
					jb.setIcon(faces[0]);
					mad = true;
				}
				jb.setVerticalAlignment(JButton.TOP);
				jb.setHorizontalAlignment(JButton.LEFT);
			}
		});
		jb.setRolloverEnabled(true);
		jb.setRolloverIcon(faces[1]);
		jb.setPressedIcon(faces[2]);
		jb.setDisabledIcon(faces[4]);
		jb.setToolTipText("Yow!");
		add(jb);
		jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (jb.isEnabled()) {
					jb.setEnabled(false);
					jb2.setText("Enable");
				} else {
					jb.setEnabled(true);
					jb2.setText("Disable");
				}
			}
		});
		add(jb2);
	}

	public static void main(String[] args) {
		run(new Faces(), 250, 125);
	}

}
