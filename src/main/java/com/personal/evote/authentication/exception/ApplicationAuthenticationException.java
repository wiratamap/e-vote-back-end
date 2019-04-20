package com.personal.evote.authentication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Something's wrong when try to authenticate user.")
public class ApplicationAuthenticationException extends RuntimeException {
}
