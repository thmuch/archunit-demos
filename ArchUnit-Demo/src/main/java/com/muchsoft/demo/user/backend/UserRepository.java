package com.muchsoft.demo.user.backend;

import com.muchsoft.demo.user.frontend.UserBean;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class UserRepository {

    private UserBean userBean = new UserBean();
}
