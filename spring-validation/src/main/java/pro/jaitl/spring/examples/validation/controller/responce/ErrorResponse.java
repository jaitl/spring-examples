package pro.jaitl.spring.examples.validation.controller.responce;

import lombok.Data;

@Data
public class ErrorResponse {
    private int code;
    private String error;
}
