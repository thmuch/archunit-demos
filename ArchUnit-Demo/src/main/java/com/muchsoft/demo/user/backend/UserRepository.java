package com.muchsoft.demo.user.backend;

import com.muchsoft.demo.user.frontend.UserBean;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

@Stateless
@LocalBean
public class UserRepository {

    void myMethod() {
        UserBean bean = new UserBean();
        System.out.println(bean);
    }
}
