package academy.jobintech.jitechpilot.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    @NotEmpty(message = "first name should not be empty")
    @Size(min = 2, max = 20, message = "first name should have be min 2 and max 20")
    private String firstName;

    @NotEmpty(message = "last name should not be empty")
    @Size(min = 2, max = 20, message = "last name should have be min 2 and max 20")
    private String lastName;

    @NotEmpty(message = "user name should not be empty")
    @Size(min = 2, max = 20, message = "user name should have be min 2 and max 20")
    private String userName;

    @NotEmpty(message = "should not be empty")
    @Email(message = "should be a valid email")
    private String email;

    @NotBlank(message = "Password must not be blank")
    @Size(min = 5, max = 20, message = "Password must be between 8 and 20 characters")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$", message = "Password must contain at least one letter and one number")
    private String password;

}
