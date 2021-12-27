package com.example.bootspecialstudy.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class JoinReqDto {

    @NotNull(message = "유저네임 키값이 없습니다.")
    @NotBlank(message = "유저네임을 입력하세요.")
    @Size(max = 20, message = "유저네임 길이를 초과하셨습니다.")
    private String username;

    @NotNull(message = "비밀번호가 없습니다.")
    private String password;

    private String phone;
}
