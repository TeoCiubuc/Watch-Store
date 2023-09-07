package com.watch.store.service;

import com.watch.store.dto.UserDetailsDto;
import com.watch.store.dto.UserDto;
import com.watch.store.dto.UserUpdateDto;
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
    public UserDto getUserDto(String email){
        Optional<User> user = userRepository.findByEmail(email);
        UserDto userDto = new UserDto();
        userDto.setFullName(user.get().getFullName());
        userDto.setAddress(user.get().getAddress());
        userDto.setPassword(user.get().getPassword());
        userDto.setEmail(user.get().getEmail());
        return userDto;
    }

    public void updateUser(UserDto userDto){
        User user = userRepository.findByFullName(userDto.getFullName());
        user.setAddress(userDto.getAddress());
        userRepository.save(user);
        saveUser(userDto);
    }

    public void updateUser(UserUpdateDto userUpdateDto){
        User user = userRepository.findByFullName(userUpdateDto.getFullName());
        user.setAddress(userUpdateDto.getAddress());
        user.setPassword(userMapper.encodePassword(userUpdateDto.getPassword()));
        userRepository.save(user);
    }
    public UserDetailsDto getUserDetailsDtoByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        User user = optionalUser.get();
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        userDetailsDto.setAddress(user.getAddress());
        userDetailsDto.setFullName(user.getFullName());
        return userDetailsDto;
    }

    public UserUpdateDto getUser(String loggedInUserEmail) {
        Optional<User> user = userRepository.findByEmail(loggedInUserEmail);
        return UserUpdateDto.builder()
                .address(user.get().getAddress())
                .fullName(user.get().getFullName()).build();

    }

}
