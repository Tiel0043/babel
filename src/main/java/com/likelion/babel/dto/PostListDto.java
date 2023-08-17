package com.likelion.babel.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class PostListDto {


    private int pageNumber; //

    private boolean hasNextNumber;

    private List<PostDto> list = new ArrayList<>();

    public PostListDto(int pageNumber, boolean hasNextNumber, List<PostDto> list) {
        this.pageNumber = pageNumber;
        this.hasNextNumber = hasNextNumber;
        this.list = list;
    }
}
