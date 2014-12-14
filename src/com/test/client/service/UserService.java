package com.test.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.test.shared.model.User;

public interface UserService extends RemoteService {
	  List<User> getUserList();
	  User getUser(String id);
}
