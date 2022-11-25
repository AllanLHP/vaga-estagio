package br.com.addsales.test.web.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

	@NotEmpty
	private String name;

	@NotEmpty
	private String email;

	@NotEmpty
	private String phone;

	@NotNull
	private LocalDate birthAt;

	@NotNull
	private Integer region;

	@NotEmpty	
	private String unity;
}
