package com.crud.service.impl;

import com.crud.Dao.UserDao;
import com.crud.domain.Func;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder pw;
    @Autowired
    private UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        List<String> funces=new ArrayList<>();
        List<Func> s = userDao.findPermissionByUserId(username);
        s.forEach(c->funces.add(c.getCode()));
        String func ="";
        for (int i = 0; i < funces.size() ;i++) {
            if(i!=funces.size()-1) {
                func += funces.get(i) + ",";
            }else {
                func+=funces.get(i);
            }
        };

        com.crud.domain.User user=userDao.findByUsernameUser(username);
        if(user==null){
            throw  new UsernameNotFoundException("用户名不存在");
        }
        String Password=pw.encode(user.getPassword());
        return new User(user.getUsername(), Password, AuthorityUtils.commaSeparatedStringToAuthorityList
                (func));
    }
}
