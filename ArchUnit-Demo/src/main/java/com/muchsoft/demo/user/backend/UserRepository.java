package com.muchsoft.demo.user.backend;

import com.muchsoft.demo.user.frontend.UserBean;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

@Stateless
@LocalBean
public class UserRepository {

    private UserBean userBean = new UserBean();
}
