/**
 * @(#)JnlpFileChooser.java - Will's practices of Project javatest.
 */
package net.will.thkij4ed.ch22.jnlp;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;

import javax.jnlp.FileContents;
import javax.jnlp.FileOpenService;
import javax.jnlp.FileSaveService;
import javax.jnlp.ServiceManager;
import javax.jnlp.UnavailableServiceException;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * Opening files on a local machine with JNLP.
 * {Requires: javax.jnlp.FileOpenService;
 * You must have javaws.jar in your classpath}
 * To create the jnlpfilechooser.jar file, do this:
 * jar cvf gui/jnlp/jnlpfilechooser.jar gui/jnlp/*.class
 * 
 * @author Will
 * @version 2012-4-13
 */
public class JnlpFileChooser extends JFrame {
	private static final long serialVersionUID = 3995486402720617260L;
	private JTextField fileName = new JTextField();
	private JButton open = new JButton("Open"), save = new JButton("Save");
	private JEditorPane ep = new JEditorPane();
	private JScrollPane jsp = new JScrollPane();
	private FileContents fileContents;

	public JnlpFileChooser() {
		JPanel p = new JPanel();
		open.addActionListener(new OpenL());
		p.add(open);
		save.addActionListener(new SaveL());
		p.add(save);
		jsp.getViewport().add(ep);
		add(jsp, BorderLayout.CENTER);
		add(p, BorderLayout.SOUTH);
		fileName.setEditable(false);
		p = new JPanel();
		p.setLayout(new GridLayout(2, 1));
		p.add(fileName);
		add(p, BorderLayout.NORTH);
		ep.setContentType("text");
		save.setEnabled(false);
	}

	class OpenL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			FileOpenService fs = null;
			try {
				fs = (FileOpenService) ServiceManager
						.lookup("javax.jnlp.FileOpenService");
			} catch (UnavailableServiceException use) {
				throw new RuntimeException(use);
			}
			if (fs != null) {
				try {
					fileContents = fs.openFileDialog(".", new String[] { "txt",
							"*" });
					if (fileContents == null)
						return;
					fileName.setText(fileContents.getName());
					ep.read(fileContents.getInputStream(), null);
				} catch (Exception exc) {
					throw new RuntimeException(exc);
				}
				save.setEnabled(true);
			}
		}
	}

	class SaveL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			FileSaveService fs = null;
			try {
				fs = (FileSaveService) ServiceManager
						.lookup("javax.jnlp.FileSaveService");
			} catch (UnavailableServiceException use) {
				throw new RuntimeException(use);
			}
			if (fs != null) {
				try {
					fileContents = fs.saveFileDialog(".",
							new String[] { "txt" }, new ByteArrayInputStream(ep
									.getText().getBytes()), fileContents
									.getName());
					if (fileContents == null)
						return;
					fileName.setText(fileContents.getName());
				} catch (Exception exc) {
					throw new RuntimeException(exc);
				}
			}
		}
	}

	public static void main(String[] args) {
		JnlpFileChooser fc = new JnlpFileChooser();
		fc.setSize(400, 300);
		fc.setVisible(true);
	}
}
