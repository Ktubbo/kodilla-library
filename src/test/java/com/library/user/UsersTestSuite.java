package com.library.user;

import com.library.dbmanager.DBManager;
import com.library.user.controller.UserController;
import com.library.user.domain.User;
import com.library.user.domain.UserDto;
import com.library.user.repository.UserRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UsersTestSuite {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserController userController;

    @AfterAll
    @Modifying
    @Query(value = "DELETE FROM users")
    public static void cleanUp(){}

    @BeforeAll
    @Modifying
    @Query(value = "ALTER TABLE users AUT0_INCREMENT=1;")
    public static void resetIds(){}
    
    @Test
    void testConnect() throws SQLException {
        //Given
        //When
        DBManager dbManager = DBManager.getInstance();
        //Then
        assertNotNull(dbManager.getConnection());
    }

    @Test
    void testGetUsers() {
        //Given
        User user = new User(1L,"Marcin","Kuryłka", Date.valueOf(LocalDate.now()));
        //When
        userRepository.save(user);
        //Then
        Long id = user.getId();
        Optional<User> readUser = userRepository.findById(id);
        assertTrue(readUser.isPresent());
    }

    @Test
    void testCreateUser() {
        //Given
        UserDto userDto = new UserDto(1L,"Marcin","Kuryłka", Date.valueOf(LocalDate.now()));
        //When
        userController.createUser(userDto);
        //Then
        Long id = userDto.getId();
        Optional<User> readUser = userRepository.findById(id);
        assertTrue(readUser.isPresent());
    }

    @Test
    void testDeleteUser() {
        //Given
        UserDto userDto = new UserDto(1L,"Marcin","Kuryłka", Date.valueOf(LocalDate.now()));
        userController.createUser(userDto);
        //When
        userController.deleteUser(userDto.getId());
        //Then
        Optional<User> readUser = userRepository.findById(userDto.getId());
        assertFalse(readUser.isPresent());
    }

    @Test
    void testUpdateUser() {
        //Given
        UserDto userDto = new UserDto(1L,"Marcin","Kuryłka", Date.valueOf(LocalDate.now()));
        userController.createUser(userDto);
        UserDto userDtoUpdated = new UserDto(1L,"Marcinek","Kuryłka",Date.valueOf(LocalDate.now()));
        //When
        userController.updateUser(userDtoUpdated);
        //Then
        Optional<User> readUser = userRepository.findById(userDto.getId());
        assertEquals(userDtoUpdated.getFirstName(),readUser.get().getFirstName());
    }

}
