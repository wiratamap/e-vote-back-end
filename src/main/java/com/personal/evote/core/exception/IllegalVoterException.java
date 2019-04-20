package com.personal.evote.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason="This voter already voted for respected category.")
public class IllegalVoterException extends RuntimeException {
}
