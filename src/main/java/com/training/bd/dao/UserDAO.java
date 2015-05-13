package com.training.bd.dao;

import java.util.List;


import com.training.bd.models.LoginObject;
import com.training.bd.models.User;
import com.training.bd.models.User;
import com.training.bd.webModels.UserFromWeb;

public interface UserDAO {
	public User isUser(String username,String password);
	public void register(UserFromWeb user);
	public boolean usernameExists(String username);
	/*public void saveUser(User user);
	public List<User> getUserList();
	public User isUser(String username,String password);*/

}
