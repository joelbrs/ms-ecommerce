package tech.joelf.ms_user.service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tech.joelf.ms_user.dtos.request.CreateUserRequest;
import tech.joelf.ms_user.dtos.request.UpdateUserRequest;
import tech.joelf.ms_user.dtos.response.UserResponse;
import tech.joelf.ms_user.model.User;
import tech.joelf.ms_user.repositories.UserRepository;

@Service
public class UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public UserResponse create(CreateUserRequest request) {
        User user = userRepository.save(modelMapper.map(request, User.class));
        return modelMapper.map(user, UserResponse.class);
    }

    @Transactional
    public UserResponse update(Long id, UpdateUserRequest request) {
        User user = userRepository.getById(id);
        BeanUtils.copyProperties(request, user, "id", "password", "email");

        return modelMapper.map(userRepository.save(user), UserResponse.class);
    }

    public UserResponse findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(user, UserResponse.class);
    }

    public Page<UserResponse> findUsers(Pageable pageable, String name) {
        Page<User> users = userRepository.findUsers(pageable, name);
        return users.map(user -> modelMapper.map(user, UserResponse.class));
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found");
        }

        try {
            userRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("User has dependencies");
        }
    }
}
