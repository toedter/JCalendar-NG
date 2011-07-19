/**
 * 
 */
package com.toedter.jcalendar.core;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Tödter
 *
 */
public class DateChooserTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testConstrucor() {
		DateChooser dateChooser = new DateChooser();
		DateTime dateTime = dateChooser.getDateTime();
		assertNotNull(dateTime);
		System.out.println(dateTime);
	}

}
