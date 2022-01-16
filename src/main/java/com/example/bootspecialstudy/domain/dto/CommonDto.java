package com.example.bootspecialstudy.domain.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonDto<T> {
    private int code;
    private T data;
}
