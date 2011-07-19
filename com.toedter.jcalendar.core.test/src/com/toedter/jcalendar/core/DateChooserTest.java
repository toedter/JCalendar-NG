/**
 * 
 */
package com.toedter.jcalendar.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the DateChooser component.
 * 
 * @author Kai Tödter
 * 
 */
public class DateChooserTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	private void checkDateTime(DateTime dateTime) {
		assertNotNull(dateTime);
		assertEquals(0, dateTime.getHourOfDay());
		assertEquals(0, dateTime.getMinuteOfHour());
		assertEquals(0, dateTime.getSecondOfMinute());
		assertEquals(0, dateTime.getMillisOfSecond());
	}

	@Test
	public void testDefaultConstrucor() {
		DateChooser dateChooser = new DateChooser();
		DateTime dateTime = dateChooser.getDateTime();
		checkDateTime(dateTime);
	}

	@Test
	public void testDateConstrucor() {
		DateChooser dateChooser = new DateChooser(new Date());
		DateTime dateTime = dateChooser.getDateTime();
		checkDateTime(dateTime);
	}

	@Test
	public void testCalendarConstrucor() {
		DateChooser dateChooser = new DateChooser(Calendar.getInstance());
		DateTime dateTime = dateChooser.getDateTime();
		checkDateTime(dateTime);
	}

}
