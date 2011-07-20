package com.toedter.jcalendar.ui.swt;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.toedter.jcalendar.core.DateChooser;
import com.toedter.jcalendar.core.IDateChooserPresenter.IDateChooserView;
import com.toedter.jcalendar.core.IDateChooserPresenter.IDayCell;

public class DateChooserView extends Composite implements IDateChooserView {

	private final DateChooser presenter;
	protected Button[] dayButtons;
	private final Composite dayPanel;

	public DateChooserView(Composite parent, int style) {
		super(parent, style);

		dayPanel = new Composite(parent, SWT.NONE);
		dayPanel.setLayout(new GridLayout(7, true));

		dayButtons = new Button[49];

		for (int i = 0; i < 49; i++) {
			dayButtons[i] = new Button(dayPanel, SWT.PUSH);
			GridData data = new GridData(GridData.FILL_BOTH);
			dayButtons[i].setLayoutData(data);
		}

		presenter = new DateChooser();
		presenter.setView(this);
	}

	@Override
	public void updateDays(List<IDayCell> days) {
		for (int i = 0; i < 42; i++) {
			String dayText = days.get(i).getDayText();
			dayButtons[i + 7].setText(dayText);
			dayButtons[i + 7].setVisible(dayText.length() != 0);
		}
	}

	@Override
	public void updateWeekDayNames(List<String> weekDayNames) {
		int index = 0;
		for (String weekDayName : weekDayNames) {
			dayButtons[index].setText(weekDayName);
			index++;
		}
	}

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("SWT DateChooser Demo");
		shell.setLayout(new GridLayout());

		DateChooserView dateChooserView = new DateChooserView(shell, SWT.NONE);
		// dateChooserView.pack();

		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
