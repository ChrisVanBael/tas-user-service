package componenttest.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUserRequestData {
    private String name = "";
    private String email = "";
    private String password = "";
    private String phoneNumber = "";
}
