package br.com.addsales.test.service;

import java.time.LocalDate;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import br.com.addsales.test.domain.Client;
import br.com.addsales.test.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {
	
	private final ClientRepository repository;
	
	public Client save(Client client) {
		calculateScore(client);
		return repository.save(client);
	}

	private void calculateScore(Client client) {
		final int score = 10;

		int age = LocalDate.now().getYear() - client.getBirthAt().getYear();
		int ageScore = getAgeScore(age);
		int regionScore = getRegionScore(client.getRegion(), client.getUnity());
		int clientScore = score + ageScore + regionScore;
		
		log.info("Client Score: {} ", clientScore);
		client.setScore(clientScore);
	}

	private int getRegionScore(Integer region, String unity) {
		if (unity.equals("sao_paulo")) {
			return 0;
		}

		HashMap<Integer, Integer> mapRegionScore = new HashMap<Integer, Integer>();
		mapRegionScore.put(0, -4);
		mapRegionScore.put(1, -1);
		mapRegionScore.put(2, -3);
		mapRegionScore.put(3, -2);
		mapRegionScore.put(4, -5);

		return mapRegionScore.get(region);
	}

	private int getAgeScore(int age) {
		if (age < 18 || age >= 100) {
			return -5;
		}

		if (age >= 39 && age <= 99) {
			return -3;
		}

		return 0;
	}
}
