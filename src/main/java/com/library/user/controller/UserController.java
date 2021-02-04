package com.library.user.controller;

import com.library.user.domain.User;
import com.library.user.domain.UserDto;
import com.library.user.domain.UserNotFoundException;
import com.library.user.mapper.UserMapper;
import com.library.service.DBService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/library")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final DBService dbService;
    private final UserMapper userMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getUsers")
    public List<UserDto> getUsers() {
        List<User> users = dbService.getAllUsers();
        return userMapper.mapToUserDtoList(users);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getUser")
    public UserDto getUser(@RequestParam Long userId) throws UserNotFoundException {
        return userMapper.mapToUserDto(dbService.getUserById(userId).orElseThrow(UserNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteUser")
    public void deleteUser(@RequestParam Long userId) {
    dbService.deleteUser(userId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto updateUser(@RequestBody UserDto userDTO) {
       User user = userMapper.mapToUser(userDTO);
       User savedUser = dbService.saveUser(user);
       return userMapper.mapToUserDto(savedUser);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDTO) {
        User user = userMapper.mapToUser(userDTO);
        user.setCreationDate(Date.valueOf(LocalDate.now()));
        dbService.saveUser(user);
    }
}
