package com.dxc.backend.service;

import com.dxc.backend.dto.RegisterDto;
import com.dxc.backend.model.UserEntity;
import com.dxc.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public UserEntity saveUser(RegisterDto registerDto) {
    UserEntity user = new UserEntity();
    user.setPassword(registerDto.getPassword());
    user.setUsername(registerDto.getUsername());

    return userRepository.save(user);
  }

  @Override
  public List<UserEntity> getAllUsers() {
    return userRepository.findAll();
  }
}
