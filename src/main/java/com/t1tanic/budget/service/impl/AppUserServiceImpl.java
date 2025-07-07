package com.t1tanic.budget.service.impl;

import com.t1tanic.budget.model.AppUser;
import com.t1tanic.budget.repository.AppUserRepository;
import com.t1tanic.budget.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository repository;

    @Autowired
    public AppUserServiceImpl(AppUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public AppUser createUser(AppUser user) {
        return repository.save(user);
    }

    @Override
    public Optional<AppUser> getUserById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<AppUser> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public AppUser updateUser(Long id, AppUser updatedUser) {
        return repository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            return repository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}
