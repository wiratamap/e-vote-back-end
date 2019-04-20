package com.personal.evote.core.controller;

import com.personal.evote.core.model.dto.VoteDto;
import com.personal.evote.core.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vote")
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PostMapping
    public ResponseEntity vote(@RequestBody VoteDto voteDto) {
        voteService.vote(voteDto);

        return new ResponseEntity<>(null, null, HttpStatus.CREATED);
    }
}
