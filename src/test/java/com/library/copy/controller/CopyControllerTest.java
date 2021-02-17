package com.library.copy.controller;

import com.library.books.domain.Book;
import com.library.books.mapper.BookMapper;
import com.library.copy.domain.Copy;
import com.library.copy.domain.CopyDto;
import com.library.copy.domain.CopyNotFoundException;
import com.library.copy.mapper.CopyMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@ExtendWith(SpringExtension.class)
class CopyControllerTest {

    @Autowired
    private CopyController copyController;
    @Autowired
    private CopyMapper copyMapper;
    @Autowired
    private BookMapper bookMapper;


    @Test
    void testGetCopies() {
        //Given
        Book book = new Book("Super Book","Super Author", 2013);
        Copy copy = new Copy(book,"Borrowed");
        //When
        copyController.createCopy(copyMapper.mapToCopyDto(copy));
        List<CopyDto> copyDtoList = copyController.getCopies();
        long copyId = copyDtoList.get(0).getId();
        //Then
        assertEquals(1,copyDtoList.size());

        try {
            assertEquals("Super Book", copyController.getCopy(copyId).getBook().getTitle());
            assertEquals("Super Author", copyController.getCopy(copyId).getBook().getAuthor());
            assertEquals(2013, copyController.getCopy(copyId).getBook().getPublishingDate());
            assertEquals("Borrowed", copyController.getCopy(copyId).getStatus());
        } catch (CopyNotFoundException e) {
            fail();
        }
    }

    @Test
    void testDeleteCopy() {
        //Given
        Book book = new Book("Super Book","Super Author", 2013);
        Copy copy = new Copy(book,"Borrowed");
        //When
        copyController.createCopy(copyMapper.mapToCopyDto(copy));
        List<CopyDto> copyDtoList = copyController.getCopies();
        long copyId = copyDtoList.get(0).getId();
        //When
        copyController.deleteCopy(copyId);
        //Then
        assertThrows(CopyNotFoundException.class,() -> {copyController.getCopy(copyId);});
    }

    @Test
    void testUpdateCopy() {
        //Given
        Book book = new Book("Super Book","Super Author", 2013);
        Copy copy = new Copy(book,"Borrowed");
        //When
        copyController.createCopy(copyMapper.mapToCopyDto(copy));
        long copyId = copyController.getCopies().get(0).getId();
        Book updatedBook = new Book("Updated Super Book", "Updated Super Author", 2014);
        Copy updatedCopy = new Copy(copyId, updatedBook, "Available");
        copyController.updateCopy(copyMapper.mapToCopyDto(updatedCopy));
        //Then
        try {
            assertEquals("Updated Super Book", copyController.getCopy(copyId).getBook().getTitle());
            assertEquals("Updated Super Author", copyController.getCopy(copyId).getBook().getAuthor());
            assertEquals(2014, copyController.getCopy(copyId).getBook().getPublishingDate());
            assertEquals("Available", copyController.getCopy(copyId).getStatus());
        } catch (CopyNotFoundException e) {
            fail();
        }
    }

    @Test
    void testCountAvailableCopies() {
        //Given
        Book book = new Book("Super Book","Super Author", 2013);
        Copy copy1 = new Copy(book,"Available");
        Copy copy2 = new Copy(book,"Borrowed");
        Copy copy3 = new Copy(book,"Available");
        //When
        copyController.createCopy(copyMapper.mapToCopyDto(copy1));
        copyController.createCopy(copyMapper.mapToCopyDto(copy2));
        copyController.createCopy(copyMapper.mapToCopyDto(copy3));
        int result = copyController.countAvailableCopies(bookMapper.mapToBookDto(book));
        //Then
        assertEquals(2,result);
    }

}