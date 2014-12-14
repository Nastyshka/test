package com.test.server;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.test.client.service.UserService;
import com.test.shared.model.User;
import com.test.shared.model.UserRole;

public class UserServiceImpl extends RemoteServiceServlet implements UserService {
	private static final long serialVersionUID = 1L;
	
	private List<User> userList = new ArrayList<User>();

	  public UserServiceImpl() {
	    User user = new User();
	    user.setId("1");
	    user.setName("Franz");
	    user.setSurname("Kafka");
	    user.setEmail("FKafka@gmail.com");
	    user.setRole(UserRole.USER);
	    userList.add(user);

	    user = new User();
	    user.setId("2");
	    user.setName("Douglas");
	    user.setSurname("Adams");
	    user.setEmail("Adams@gmail.com");
	    user.setRole(UserRole.USER);
	    userList.add(user);
	  }

	  @Override
	  public User getUser(String id) {

	    for (Object object : userList) {
	      if (((User) object).getId().equals(id))
	        return ((User) object);
	    }
	    return null;
	  }

	  @Override
	  public List<User> getUserList() {
	    return userList;
	  }

}
