package com.personal.evote.lookup.appuser.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User is invalid.")
public class UserNotFoundException extends RuntimeException {
}
