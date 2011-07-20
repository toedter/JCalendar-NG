package com.toedter.jcalendar.core;

import java.util.List;

public interface IDateChooserPresenter {
	public interface IDayCell {
		public int getDay();

		public boolean isEnabled();
	}

	public interface IDateChooserView {
		public void updateDays(List<IDayCell> days);

		public void updateWeekDayNames(List<String> weekDayNames);
	}

	public void setView(IDateChooserView view);
}
