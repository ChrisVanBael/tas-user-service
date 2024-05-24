package componenttest.models;

import lombok.Data;

@Data
public class ErrorResponseData {
    private String message;
    private int statusCode;
}

