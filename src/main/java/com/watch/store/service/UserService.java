package com.watch.store.service;

import com.watch.store.dto.UserDetailsDto;
import com.watch.store.dto.UserDto;
import com.watch.store.entity.User;
import com.watch.store.mapper.UserMapper;
import com.watch.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public void saveUser(UserDto userDto) {
        User user = userMapper.mapp(userDto);
        userRepository.save(user);
    }

    public UserDetailsDto getUserDetailsDto(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        userDetailsDto.setFullName(user.get().getFullName());
        userDetailsDto.setAddress(user.get().getAddress());
        return userDetailsDto;
    }
}
