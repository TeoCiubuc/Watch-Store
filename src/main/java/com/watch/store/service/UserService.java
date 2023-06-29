package com.watch.store.service;

import com.watch.store.dto.UserDto;
import com.watch.store.entity.User;
import com.watch.store.mapper.UserMapper;
import com.watch.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    public void saveUser(UserDto userDto){
        User user = userMapper.mapp(userDto);
        userRepository.save(user);
    }
}
