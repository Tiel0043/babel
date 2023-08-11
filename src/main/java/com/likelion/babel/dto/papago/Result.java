package com.likelion.babel.dto.papago;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Result {
    private String srcLangType;
    private String tarLangType;
    private String translatedText;
}
