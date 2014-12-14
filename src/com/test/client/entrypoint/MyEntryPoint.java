package com.test.client.entrypoint;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.test.client.MySplitLayoutPanel;

public class MyEntryPoint implements EntryPoint {

	@Override
	public void onModuleLoad() {
		RootLayoutPanel.get().add(new MySplitLayoutPanel());
	}

}
