package com.library.user.controller;

import com.library.user.domain.User;
import com.library.user.domain.UserDto;
import com.library.user.domain.UserNotFoundException;
import com.library.user.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UsersControllerTestSuite {

    @Autowired
    private UserController userController;
    @Autowired
    private UserMapper userMapper;

    @Test
    void testGetUsers() {
        //Given
        userController.createUser(userMapper.mapToUserDto(new User("Marcin","Kuryłka")));
        //When
        List<UserDto> createdUsers = userController.getUsers();
        //Then
        assertEquals(1,createdUsers.size());
    }


    @Test
    void testDeleteUser() {
        //Given
        UserDto userDto = userMapper.mapToUserDto(new User("Marcin","Kuryła"));
        userController.createUser(userDto);
        final long userId = userController.getUsers().get(0).getId();
        //When
        userController.deleteUser(userId);
        //Then
        assertThrows(UserNotFoundException.class,() -> {userController.getUser(userId);});
    }

    @Test
    void testUpdateUser() {
        //Given
        UserDto userDtoUpdated = new UserDto(1L,"Marcinek","Kuryłka",Date.valueOf(LocalDate.now()));
        //When
        userController.updateUser(userDtoUpdated);
        final long userId = userController.getUsers().get(0).getId();
        //Then
        try {
            UserDto resultUserDto = userController.getUser(userId);
            assertEquals("Marcinek",resultUserDto.getFirstName());
        } catch (Exception e) {
            fail();
        }
    }

}
