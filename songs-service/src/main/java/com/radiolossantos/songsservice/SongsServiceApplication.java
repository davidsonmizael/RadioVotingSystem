package com.radiolossantos.songsservice;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.radiolossantos.songsservice.entity.Song;
import com.radiolossantos.songsservice.service.SongService;

@SpringBootApplication
public class SongsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SongsServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(SongService songService) {
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Song>> typeReference = new TypeReference<List<Song>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/data/spotify-top100-2018.json");
			try {
				List<Song> songs = mapper.readValue(inputStream,typeReference);
				songService.save(songs);
			} catch (IOException e){
				e.printStackTrace();
			}
		};
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}
}
