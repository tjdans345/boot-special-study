package com.example.bootspecialstudy.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonDto<T> {
    private int code;
    private T data;
}
