package ensa.pay.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class ApiExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(value =MicroServiceException.class)
    @ResponseBody
    public ResponseEntity<ExceptionDto> handlePortalException(MicroServiceException businessException) {
        ExceptionDto dto = MicroServiceExceptionMapper.toExceptionDto(businessException);
        LOGGER.error("GcmException handled", businessException);
        LOGGER.error("Error du to {} . More details : {}", businessException.getExceptionCode(), dto);
        return new ResponseEntity<>(dto, HttpStatus.valueOf(businessException.getExceptionCode().getStatus()));
    }

}
