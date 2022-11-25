package br.com.addsales.test.web;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.addsales.test.domain.Client;
import br.com.addsales.test.service.ClientService;
import br.com.addsales.test.web.dto.ClientDTO;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class IndexController {

	private static final String PAGE_INDEX = "client/index";

	private static final String PAGE_SUCCESS = "client/success";

	private final ClientService service;

	private final ModelMapper mapper;

	@GetMapping
	public String index(ClientDTO clientDTO) {
		return PAGE_INDEX;
	}

	@PostMapping
	public String save(@Valid ClientDTO clientDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return index(clientDTO);
		}

		final Client client = mapper.map(clientDTO, Client.class);
		service.save(client);
		
		return PAGE_SUCCESS;
	}
}
