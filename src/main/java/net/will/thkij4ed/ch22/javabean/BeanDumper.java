/**
 * @(#)BeanDumper.java - Will's practices of Project javatest.
 */
package net.will.thkij4ed.ch22.javabean;

import static net.will.thkij4ed.ch22.SwingConsole.run;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.BeanInfo;
import java.beans.EventSetDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Introspecting a Bean.
 * 
 * @author Will
 * @version 2012-4-18
 */
public class BeanDumper extends JFrame {
	private static final long serialVersionUID = 1482125958261686086L;

	private JTextField query = new JTextField(20);
	private JTextArea results = new JTextArea();

	public void print(String s) {
		results.append(s + "\n");
	}

	public void dump(Class<?> bean) {
		results.setText("");
		BeanInfo bi = null;
		try {
			bi = Introspector.getBeanInfo(bean, Object.class);
		} catch (IntrospectionException e) {
			print("Couldn’t introspect " + bean.getName());
			return;
		}
		for (PropertyDescriptor d : bi.getPropertyDescriptors()) {
			Class<?> p = d.getPropertyType();
			if (p == null)
				continue;
			print("Property type:\n " + p.getName() + "Property name:\n "
					+ d.getName());
			Method readMethod = d.getReadMethod();
			if (readMethod != null)
				print("Read method:\n " + readMethod);
			Method writeMethod = d.getWriteMethod();
			if (writeMethod != null)
				print("Write method:\n " + writeMethod);
			print("====================");
		}
		print("Public methods:");
		for (MethodDescriptor m : bi.getMethodDescriptors())
			print(m.getMethod().toString());
		print("======================");
		print("Event support:");
		for (EventSetDescriptor e : bi.getEventSetDescriptors()) {
			print("Listener type:\n " + e.getListenerType().getName());
			for (Method lm : e.getListenerMethods())
				print("Listener method:\n " + lm.getName());
			for (MethodDescriptor lmd : e.getListenerMethodDescriptors())
				print("Method descriptor:\n " + lmd.getMethod());
			Method addListener = e.getAddListenerMethod();
			print("Add Listener Method:\n " + addListener);
			Method removeListener = e.getRemoveListenerMethod();
			print("Remove Listener Method:\n " + removeListener);
			print("====================");
		}
	}

	class Dumper implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String name = query.getText();
			Class<?> c = null;
			try {
				c = Class.forName(name);
			} catch (ClassNotFoundException ex) {
				results.setText("Couldn’t find " + name);
				return;
			}
			dump(c);
		}
	}

	public BeanDumper() {
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout());
		p.add(new JLabel("Qualified bean name:"));
		p.add(query);
		add(BorderLayout.NORTH, p);
		add(new JScrollPane(results));
		Dumper dmpr = new Dumper();
		query.addActionListener(dmpr);
		query.setText("frogbean.Frog");
		// Force evaluation
		dmpr.actionPerformed(new ActionEvent(dmpr, 0, ""));
	}

	public static void main(String[] args) {
		run(new BeanDumper(), 600, 500);
	}

}
