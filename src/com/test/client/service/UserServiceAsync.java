package com.test.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.test.shared.model.User;

public interface UserServiceAsync {
	  void getUserList(AsyncCallback<List<User>> callback);
	  void getUser(String id, AsyncCallback<User> callback);
}
