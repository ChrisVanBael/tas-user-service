package componenttest.models;

import lombok.Data;

@Data
public class PostUserResponseData {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
}
