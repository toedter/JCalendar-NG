/*******************************************************************************
 * Copyright (c) 2011 Kai Tödter
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Kai Tödter - initial API and implementation
 *******************************************************************************/

package com.toedter.jcalendar.core;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 * This class provides functionality for choosing a date.
 * 
 * @author Kai Toedter
 * 
 */
public class DateChooser implements IDateChooserPresenter {

	private DateTime dateTime;
	private IDateChooserView view;
	private final List<IDayCell> dayCells;
	private final Locale locale;
	private List<String> weekdays;

	private class DayCell implements IDateChooserPresenter.IDayCell {
		String dayText;

		@Override
		public String getDayText() {
			return dayText;
		}

		@Override
		public boolean isEnabled() {
			return false;
		}

	}

	/**
	 * Constructs an instance using today.
	 */
	public DateChooser() {
		this(new DateTime());
	}

	/**
	 * Constructs an instance from a given Date.
	 * 
	 * @param date
	 *            the initializing Date
	 */
	public DateChooser(Date date) {
		this(new DateTime(date));
	}

	/**
	 * Constructs an instance from a given Calendar.
	 * 
	 * @param calendar
	 *            the initializing Calendar
	 */
	public DateChooser(Calendar calendar) {
		this(new DateTime(calendar));
	}

	/**
	 * Constructs an instance from a given DateTime.
	 * 
	 * @param calendar
	 *            the initializing DateTime
	 */
	public DateChooser(DateTime dateTime) {
		// normalize to year/month/date only
		this.dateTime = new DateTime(dateTime.getYear(), dateTime.getMonthOfYear(), dateTime.getDayOfMonth(), 0, 0, 0,
				0);
		dayCells = new ArrayList<IDayCell>(42);
		for (int i = 0; i < 42; i++) {
			DayCell dayCell = new DayCell();
			dayCells.add(dayCell);
		}

		locale = Locale.getDefault();
		computeWeekdayNames();
		computeDayCellValues();
	}

	private void computeDayCellValues() {
		DateTime firstDayThisMonth = new DateTime(dateTime.getYear(), dateTime.getMonthOfYear(), 1, 0, 0, 0, 0);
		DateTime firstDayNextMonth = firstDayThisMonth.plusMonths(1).withDayOfMonth(1);
		int dayOfWeek = firstDayThisMonth.dayOfWeek().get();

		Days d = Days.daysBetween(firstDayThisMonth, firstDayNextMonth);
		int daysInThisMonth = d.getDays();
		System.out.println(daysInThisMonth);

		int index = 2;
		for (IDayCell dayCell : dayCells) {
			if (index <= dayOfWeek || index - dayOfWeek > daysInThisMonth) {
				((DayCell) dayCell).dayText = "";
			} else {
				((DayCell) dayCell).dayText = "" + (index - dayOfWeek);
			}
			index++;
		}
	}

	private void computeWeekdayNames() {
		int firstDayOfWeek = Calendar.getInstance().getFirstDayOfWeek();
		DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(locale);
		String[] dayNames = dateFormatSymbols.getShortWeekdays();
		weekdays = new ArrayList<String>();

		int day = firstDayOfWeek;

		for (int i = 0; i < 7; i++) {
			weekdays.add(dayNames[day]);
			if (day < 7) {
				day++;
			} else {
				day -= 6;
			}
		}
	}

	public DateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(DateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Date getDate() {
		return dateTime.toDate();
	}

	public Calendar getCalendar() {
		return dateTime.toCalendar(null);
	}

	@Override
	public void setView(IDateChooserView view) {
		this.view = view;
		this.view.updateDays(dayCells);
		this.view.updateWeekDayNames(weekdays);
	}
}
