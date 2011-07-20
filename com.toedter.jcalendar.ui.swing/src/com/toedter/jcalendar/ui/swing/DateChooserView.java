package com.toedter.jcalendar.ui.swing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.toedter.jcalendar.core.DateChooser;
import com.toedter.jcalendar.core.IDateChooserPresenter;
import com.toedter.jcalendar.core.IDateChooserPresenter.IDayCell;

@SuppressWarnings("serial")
public class DateChooserView extends JPanel implements
		IDateChooserPresenter.IDateChooserView {

	private final DateChooser presenter;
	protected JButton[] dayButtons;
	private final JPanel dayPanel;

	public DateChooserView() {
		dayPanel = new JPanel();
		dayPanel.setLayout(new GridLayout(7, 7));
		dayButtons = new JButton[49];

		for (int i = 0; i < 49; i++) {
			dayButtons[i] = new JButton("x");

			dayButtons[i].setMargin(new Insets(0, 0, 0, 0));
			dayButtons[i].setFocusPainted(false);
			dayPanel.add(dayButtons[i]);
		}

		presenter = new DateChooser();
		presenter.setView(this);

		setLayout(new BorderLayout());
		add(dayPanel, BorderLayout.CENTER);
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

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				WindowListener l = new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}
				};

				JFrame frame = new JFrame("Swing Date Chooser View");
				frame.addWindowListener(l);

				DateChooserView dateChooserView = new DateChooserView();
				frame.getContentPane().add(dateChooserView);
				frame.pack();

				frame.setBounds(200, 200, 200, 200);
				frame.setVisible(true);
			}
		});
	}
}
