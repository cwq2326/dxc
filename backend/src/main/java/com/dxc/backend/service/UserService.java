package com.dxc.backend.service;

import com.dxc.backend.dto.RegisterDto;
import com.dxc.backend.model.UserEntity;

import java.util.List;

public interface UserService {
  UserEntity saveUser(RegisterDto registerDto);

  List<UserEntity> getAllUsers();
}
