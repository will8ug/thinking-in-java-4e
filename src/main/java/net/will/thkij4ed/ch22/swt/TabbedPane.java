/**
 * @(#)TabbedPane.java - Will's practices of Project javatest.
 */
package net.will.thkij4ed.ch22.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

/**
 * Placing SWT components in tabbed panes.
 * 
 * @author Will
 * @version 2012-4-20
 */
public class TabbedPane implements SWTApplication {

	private static TabFolder folder;
	private static Shell shell;

	/*
	 * (non-Javadoc)
	 * @see net.will.thkij4ed.ch22.swt.SWTApplication#createContents(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createContents(Composite parent) {
		shell = parent.getShell();
		parent.setLayout(new FillLayout());
		folder = new TabFolder(shell, SWT.BORDER);
		labelTab();
		directoryDialogTab();
		buttonTab();
		sliderTab();
		scribbleTab();
		browserTab();
	}

	public static void labelTab() {
		TabItem tab = new TabItem(folder, SWT.CLOSE);
		tab.setText("A Label"); // Text on the tab
		tab.setToolTipText("A simple label");
		Label label = new Label(folder, SWT.CENTER);
		label.setText("Label text");
		tab.setControl(label);
	}

	public static void directoryDialogTab() {
		TabItem tab = new TabItem(folder, SWT.CLOSE);
		tab.setText("Directory Dialog");
		tab.setToolTipText("Select a directory");
		final Button b = new Button(folder, SWT.PUSH);
		b.setText("Select a Directory");
		b.addListener(SWT.MouseDown, new Listener() {
			public void handleEvent(Event e) {
				DirectoryDialog dd = new DirectoryDialog(shell);
				String path = dd.open();
				if (path != null)
					b.setText(path);
			}
		});
		tab.setControl(b);
	}

	public static void buttonTab() {
		TabItem tab = new TabItem(folder, SWT.CLOSE);
		tab.setText("Buttons");
		tab.setToolTipText("Different kinds of Buttons");
		Composite composite = new Composite(folder, SWT.NONE);
		composite.setLayout(new GridLayout(4, true));
		for (int dir : new int[] { SWT.UP, SWT.RIGHT, SWT.LEFT, SWT.DOWN }) {
			Button b = new Button(composite, SWT.ARROW | dir);
			b.addListener(SWT.MouseDown, listener);
		}
		newButton(composite, SWT.CHECK, "Check button");
		newButton(composite, SWT.PUSH, "Push button");
		newButton(composite, SWT.RADIO, "Radio button");
		newButton(composite, SWT.TOGGLE, "Toggle button");
		newButton(composite, SWT.FLAT, "Flat button");
		tab.setControl(composite);
	}

	private static Listener listener = new Listener() {
		public void handleEvent(Event e) {
			MessageBox m = new MessageBox(shell, SWT.OK);
			m.setMessage(e.toString());
			m.open();
		}
	};

	private static void newButton(Composite composite, int type, String label) {
		Button b = new Button(composite, type);
		b.setText(label);
		b.addListener(SWT.MouseDown, listener);
	}

	public static void sliderTab() {
		TabItem tab = new TabItem(folder, SWT.CLOSE);
		tab.setText("Sliders and Progress bars");
		tab.setToolTipText("Tied Slider to ProgressBar");
		Composite composite = new Composite(folder, SWT.NONE);
		composite.setLayout(new GridLayout(2, true));
		final Slider slider = new Slider(composite, SWT.HORIZONTAL);
		final ProgressBar progress = new ProgressBar(composite, SWT.HORIZONTAL);
		slider.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				progress.setSelection(slider.getSelection());
			}
		});
		tab.setControl(composite);
	}

	public static void scribbleTab() {
		TabItem tab = new TabItem(folder, SWT.CLOSE);
		tab.setText("Scribble");
		tab.setToolTipText("Simple graphics: drawing");
		final Canvas canvas = new Canvas(folder, SWT.NONE);
		ScribbleMouseListener sml = new ScribbleMouseListener();
		canvas.addMouseListener(sml);
		canvas.addMouseMoveListener(sml);
		tab.setControl(canvas);
	}

	private static class ScribbleMouseListener extends MouseAdapter implements
			MouseMoveListener {
		private Point p = new Point(0, 0);

		public void mouseMove(MouseEvent e) {
			if ((e.stateMask & SWT.BUTTON1) == 0)
				return;
			GC gc = new GC((Canvas) e.widget);
			gc.drawLine(p.x, p.y, e.x, e.y);
			gc.dispose();
			updatePoint(e);
		}

		public void mouseDown(MouseEvent e) {
			updatePoint(e);
		}

		private void updatePoint(MouseEvent e) {
			p.x = e.x;
			p.y = e.y;
		}
	}

	public static void browserTab() {
		TabItem tab = new TabItem(folder, SWT.CLOSE);
		tab.setText("A Browser");
		tab.setToolTipText("A Web browser");
		Browser browser = null;
		try {
			browser = new Browser(folder, SWT.NONE);
		} catch (SWTError e) {
			Label label = new Label(folder, SWT.BORDER);
			label.setText("Could not initialize browser");
			tab.setControl(label);
		}
		if (browser != null) {
			browser.setUrl("http://www.mindview.net");
			tab.setControl(browser);
		}
	}

	public static void main(String[] args) {
		SWTConsole.run(new TabbedPane(), 800, 600);
	}

}
