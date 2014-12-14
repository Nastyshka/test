package com.test.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.SelectionCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionModel;
import com.test.shared.model.User;
import com.test.shared.model.UserRole;

public class MySplitLayoutPanel extends Composite {

	private final MyConstants constants = GWT.create(MyConstants.class);
	
	@UiField 
	MyStyle style;
	@UiField
	SplitLayoutPanel splitLayoutPanel;
	@UiField(provided = true)
	DataGrid<User> dataGrid;
	@UiField
	Label emailLabel;
	@UiField
	Label surnameLabel;
	@UiField
	Button goButton;
	@UiField
	CheckBox disableCheck;

	private ListDataProvider<User> dataProvider = new ListDataProvider<User>();

	@UiHandler("goButton")
	void onButtonClicked(ClickEvent event) {
		Window.alert(constants.buttonGo());
	}
	
	@UiHandler("disableCheck")
	void onRadioSelected(ClickEvent event) {
		if(disableCheck.getValue()){
			for(User user : dataProvider.getList()){
				dataGrid.getSelectionModel().setSelected(user, false);
			}
			goButton.setEnabled(false);
			disableCheck.setValue(true);;
		} else {
			disableCheck.setValue(false);
		}
	}
	public Widget onInitialize() {
		
		dataGrid = new DataGrid<User>(User.KEY_PROVIDER);
		dataGrid.setWidth("100%");
		dataGrid.setAutoHeaderRefreshDisabled(true);
		// Set the message to display when the table is empty.
		dataGrid.setEmptyTableWidget(new Label(constants.cwDataGridEmpty()));

		// Add a selection model so we can select cells.
		SelectionModel<User> selectionModel = new MultiSelectionModel<User>(
				User.KEY_PROVIDER);
		dataGrid.setSelectionModel(selectionModel,
				DefaultSelectionEventManager.<User> createCheckboxManager());

		// Initialize the columns.
		initTableColumns(selectionModel);

		// fill data
		List<User> users = dataProvider.getList();
		for (int i = 0; i < 7; i++) {
			users.add(new User());
		}

		// Add the CellList to the adapter in the dataProvider.
		dataProvider.addDataDisplay(dataGrid);
		MyUiBinderSplitLayout uiBinder = GWT
				.create(MyUiBinderSplitLayout.class);
		return uiBinder.createAndBindUi(this);
	}

	private void initTableColumns(final SelectionModel<User> selectionModel) {
		// Checkbox column. This table will uses a checkbox column for
		// selection.
		Column<User, Boolean> checkColumn = new Column<User, Boolean>(
				new CheckboxCell(true, false)) {
			@Override
			public Boolean getValue(User object) {
				// Get the value from the selection model.
				if(selectionModel.isSelected(object)){
					emailLabel.setText(constants.labelEmail() + " " + object.getEmail());
					surnameLabel.setText(constants.labelSurname() + " " + object.getSurname());
					goButton.setStyleName(style.disabled(), false);
					goButton.addStyleName(style.enabled());
					goButton.setEnabled(true);
				}
				return selectionModel.isSelected(object);
			}
		};
		dataGrid.addColumn(checkColumn, SafeHtmlUtils.fromSafeConstant("<br/>"));
		dataGrid.setColumnWidth(checkColumn, 40, Unit.PX);

		// Id.
		Column<User, String> idColumn = new Column<User, String>(new TextCell()) {
			@Override
			public String getValue(User object) {
				return object.getId();
			}
		};

		dataGrid.addColumn(idColumn, constants.cwDataGridColumnId());
		dataGrid.setColumnWidth(idColumn, 20, Unit.PCT);

		// Name
		Column<User, String> firstNameColumn = new Column<User, String>(
				new TextCell()) {
			@Override
			public String getValue(User object) {
				return object.getName();
			}
		};
		
		firstNameColumn.hashCode();
		dataGrid.addColumn(firstNameColumn,
				constants.cwDataGridColumnFirstName());
		dataGrid.setColumnWidth(firstNameColumn, 20, Unit.PCT);

		// UserRole.
		final UserRole[] roles = UserRole.getRoles();
		List<String> UserRoleNames = new ArrayList<String>();
		for (UserRole UserRole : roles) {
			UserRoleNames.add(UserRole.toString());
		}
		SelectionCell UserRoleCell = new SelectionCell(UserRoleNames);
		Column<User, String> UserRoleColumn = new Column<User, String>(
				UserRoleCell) {
			@Override
			public String getValue(User object) {
				return object.getRole().toString();
			}
		};
		dataGrid.addColumn(UserRoleColumn, constants.cwDataGridColumnUserRole());

		UserRoleColumn.setFieldUpdater(new FieldUpdater<User, String>() {
			@Override
			public void update(int index, User object, String value) {
				for (UserRole UserRole : roles) {
					if (UserRole.toString().equals(value)) {
						object.setRole(UserRole);
					}
				}
				dataProvider.refresh();
			}
		});
		dataGrid.setColumnWidth(UserRoleColumn, 130, Unit.PX);
	}

	interface MyUiBinderSplitLayout extends
		UiBinder<Widget, MySplitLayoutPanel> {
	}
	
	interface MyStyle extends CssResource {
	    String enabled();
	    String disabled();
	    String buttons();
	    String labels();
	  }

	public MySplitLayoutPanel() {
		initWidget(onInitialize());
		goButton.addStyleName(style.disabled());
		goButton.addStyleName(style.buttons());
		goButton.setText(constants.buttonGo());
		goButton.setEnabled(false);
		disableCheck.setText(constants.labelDisableSelection());
		disableCheck.setValue(false);
	}
}
