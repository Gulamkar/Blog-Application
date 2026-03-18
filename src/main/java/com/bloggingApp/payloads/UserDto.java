package com.bloggingApp.payloads;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	private int Id;
	@NotNull
	@Size(min=4, message="UserName must be min of 4 character")
	private String name;
	@Email(message="Email address is not valid !!")
	private String email;
	@NotNull
	@Size(min=3, max=10, message="Password must be min of 3 and max of 10")
	private String password;
	@NotNull
	private String about;

}
