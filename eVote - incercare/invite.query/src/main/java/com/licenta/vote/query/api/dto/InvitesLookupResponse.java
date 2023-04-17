package com.licenta.vote.query.api.dto;

import com.licenta.vote.common.dtos.BaseResponse;
import com.licenta.vote.query.domain.Invite;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class InvitesLookupResponse extends BaseResponse {
    private List<Invite> invites;
    public InvitesLookupResponse(String message){
        super(message);
    }
}
