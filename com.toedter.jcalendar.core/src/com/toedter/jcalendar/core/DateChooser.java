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

import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;

/**
 * This class provides functionality for choosing a date.
 * 
 * @author Kai Tödter
 * 
 */
public class DateChooser {

	private DateTime dateTime;

	/**
	 * Constructs an instance using today.
	 */
	public DateChooser() {
		this(new DateTime());
	}

	/**
	 * Constructs an instance from a given Date.
	 * 
	 * @param date the initializing Date
	 */
	public DateChooser(Date date) {
		this(new DateTime(date));
	}

	/**
	 * Constructs an instance from a given Calendar.
	 * 
	 * @param calendar the initializing Calendar
	 */
	public DateChooser(Calendar calendar) {
		this(new DateTime(calendar));
	}

	/**
	 * Constructs an instance from a given DateTime.
	 * 
	 * @param calendar the initializing DateTime
	 */
	public DateChooser(DateTime dateTime) {
		// normalize to year/month/date only
		this.dateTime = new DateTime(dateTime.getYear(),
				dateTime.getMonthOfYear(), dateTime.getDayOfMonth(), 0,
				0, 0, 0);
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


}
