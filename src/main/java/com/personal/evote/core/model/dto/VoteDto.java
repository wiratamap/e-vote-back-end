package com.personal.evote.core.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class VoteDto {
    private UUID voterId;

    private UUID candidateId;
}
