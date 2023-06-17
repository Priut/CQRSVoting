package com.licenta.vote.query.api.dto;

import com.licenta.vote.common.dtos.BaseResponse;
import com.licenta.vote.query.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserLookupResponse extends BaseResponse {
    private List<User> users;
    public UserLookupResponse(String message){
        super(message);
    }
}
