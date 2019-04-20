package com.personal.evote.lookup.candidate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Candidate not found.")
public class CandidateNotFoundException extends RuntimeException {
}
