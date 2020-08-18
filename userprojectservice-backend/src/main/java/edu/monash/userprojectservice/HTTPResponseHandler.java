package edu.monash.userprojectservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
public class HTTPResponseHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public static class BadRequestException extends RuntimeException {
    }

    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public static class ForbiddenException extends RuntimeException {
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public static class NotFoundException extends RuntimeException {
    }

}
