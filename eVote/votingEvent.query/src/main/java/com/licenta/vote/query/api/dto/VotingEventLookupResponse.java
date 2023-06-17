package com.licenta.vote.query.api.dto;

import com.licenta.vote.common.dtos.BaseResponse;
import com.licenta.vote.query.domain.VotingEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class VotingEventLookupResponse extends BaseResponse {
    private List<VotingEvent> votingEvents;
    public VotingEventLookupResponse(String message){
        super(message);
    }
}
