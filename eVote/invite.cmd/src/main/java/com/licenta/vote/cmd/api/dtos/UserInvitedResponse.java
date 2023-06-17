package com.licenta.vote.cmd.api.dtos;

import com.licenta.vote.common.dtos.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInvitedResponse extends BaseResponse {
    private String id;
    public UserInvitedResponse(String message, String id){
        super(message);
        this.id = id;
    }
}
