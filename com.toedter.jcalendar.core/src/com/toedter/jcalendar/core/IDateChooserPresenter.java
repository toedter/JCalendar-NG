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

import java.util.List;

public interface IDateChooserPresenter {
	public interface IDayCell {
		public String getDayText();

		public boolean isEnabled();
	}

	public interface IDateChooserView {
		public void updateDays(List<IDayCell> days);

		public void updateWeekDayNames(List<String> weekDayNames);
	}

	public void setView(IDateChooserView view);
}
