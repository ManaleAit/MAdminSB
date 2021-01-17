package ensa.pay.exception;

public class MicroServiceExceptionMapper {

    private MicroServiceExceptionMapper() {
    }

    public static ExceptionDto toExceptionDto(MicroServiceException exception) {
        return new ExceptionDto()
                .setCode(exception.getExceptionCode().name())
                .setMessage(exception.getMsg())
                .setTicketId(exception.getTickedId())
                .setTime(exception.getTime())
                .setStatus(exception.getExceptionCode().getStatus())
                .setErrors(exception.getErrors());
    }
}
