package com.library.copy.controller;

import com.library.books.domain.Book;
import com.library.books.domain.BookDto;
import com.library.books.mapper.BookMapper;
import com.library.copy.domain.Copy;
import com.library.copy.domain.CopyDto;
import com.library.copy.domain.CopyNotFoundException;
import com.library.copy.mapper.CopyMapper;
import com.library.service.DBService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/library")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CopyController {

    private final DBService dbService;
    private final CopyMapper copyMapper;
    private final BookMapper bookMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getCopies")
    public List<CopyDto> getCopies() {
        List<Copy> copies = dbService.getAllCopies();
        return copyMapper.mapToCopyDtoList(copies);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getCopy")
    public CopyDto getCopy(@RequestParam Long copyId) throws CopyNotFoundException {
        return copyMapper.mapToCopyDto(dbService.getCopyById(copyId).orElseThrow(CopyNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteCopy")
    public void deleteCopy(@RequestParam Long copyId) { dbService.deleteCopy(copyId);}

    @RequestMapping(method = RequestMethod.PUT, value = "updateCopy", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CopyDto updateCopy(@RequestBody CopyDto copyDto) {
        Copy copy = copyMapper.mapToCopy(copyDto);
        Copy savedCopy = dbService.saveCopy(copy);
        return copyMapper.mapToCopyDto(savedCopy);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createCopy", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createCopy(@RequestBody CopyDto copyDto) {
        Copy copy = copyMapper.mapToCopy(copyDto);
        dbService.saveCopy(copy);
    }

    @RequestMapping(method = RequestMethod.GET, value = "countAvailableCopies", consumes = MediaType.APPLICATION_JSON_VALUE)
    public int countAvailableCopies(@RequestBody BookDto bookDto) {
        return dbService.countAvailableCopies(bookMapper.mapToBook(bookDto)).size();
    }
}
