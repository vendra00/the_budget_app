package com.t1tanic.budget.service;

import com.t1tanic.budget.model.AppUser;

import java.util.List;
import java.util.Optional;

public interface AppUserService {
    AppUser createUser(AppUser user);
    Optional<AppUser> getUserById(Long id);
    List<AppUser> getAllUsers();
    AppUser updateUser(Long id, AppUser updatedUser);
    void deleteUser(Long id);
}
