package com.library.copy.mapper;

import com.library.copy.domain.Copy;
import com.library.copy.domain.CopyDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CopyMapper {

    public Copy mapToCopy(CopyDto copyDto) {
        return new Copy(
                copyDto.getId(),
                copyDto.getBook(),
                copyDto.getStatus()
        );
    }

    public CopyDto mapToCopyDto(Copy copy) {
        return new CopyDto(
                copy.getId(),
                copy.getBook(),
                copy.getStatus()
        );
    }

    public List<CopyDto> mapToCopyDtoList(List<Copy> copiesList) {
        return copiesList.stream()
                .map(this::mapToCopyDto)
                .collect(Collectors.toList());
    }

}
