package com.practice.Blog.API.services.implementation;

import com.practice.Blog.API.DTO.CreateANew.CreateANewUserDTO;
import com.practice.Blog.API.DTO.UserDTO;
import com.practice.Blog.API.entity.User;
import com.practice.Blog.API.exceptions.ResourceNotFoundException;
import com.practice.Blog.API.repository.UserRepository;
import com.practice.Blog.API.services.UserServices;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServicesImplementation implements UserServices {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDTO Createauser(CreateANewUserDTO createANewUserDTO) {
        User user = modelMapper.map(createANewUserDTO,User.class);
        userRepository.save(user);
        return modelMapper.map(user,UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList
                .stream()
                .map((element) -> modelMapper.map(element, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getOneUser(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with id:"+id));
        return modelMapper.map(user,UserDTO.class);
    }

    @Override
    public UserDTO updateUser(Long id, CreateANewUserDTO createANewUserDTO) {
        User user = userRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with id:"+id));
        modelMapper.map(createANewUserDTO,user);
        userRepository.save(user);
        return modelMapper.map(user,UserDTO.class);
    }

    @Override
    public void deleteUser(Long id) {
        if(!userRepository.existsById(id)){
            throw new ResourceNotFoundException("User not found with id:"+id);
        }
        userRepository.deleteById(id);
    }

}
