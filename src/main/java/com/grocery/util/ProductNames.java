package com.grocery.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductNames {
    SOUP("soup"),BREAD("bread"),MILK("milk"),APPLES("apples");
    private String name;
}
