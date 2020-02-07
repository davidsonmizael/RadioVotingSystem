package com.radiolossantos.songsservice.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.radiolossantos.songsservice.dao.SongDAO;
import com.radiolossantos.songsservice.dto.SongDTO;
import com.radiolossantos.songsservice.entity.Song;
import com.radiolossantos.songsservice.service.impl.SongServiceImpl;

@ExtendWith(MockitoExtension.class)
public class SongServiceImplTest {
	
	@Mock
	private SongDAO songDAO;
	private List<Song> songList;

	@InjectMocks
	@Spy
	private SongServiceImpl songService;
	
	@BeforeEach
	public void setup() {		
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Song>> typeReference = new TypeReference<List<Song>>(){};
		InputStream inputStream = TypeReference.class.getResourceAsStream("/data/spotify-top100-2018.json");
		try {
			songList = mapper.readValue(inputStream,typeReference);
		} catch (IOException e){
			System.out.println("Unable to load songs for testing: " + e.getMessage());
		}
		
	}
	
	@Test
	public void testListAll() {
		when(songDAO.findAll()).thenReturn(songList);
		List<SongDTO> songListDTO = songService.getAll();
		assertEquals(songList.size(), songListDTO.size());
		
		Song randSong = songList.get(new Random().nextInt(songList.size()));
		assertTrue(songListDTO.stream().anyMatch(s -> s.getName().equals(randSong.getName())));
	}

	@Test
	public void testGetOneById() {
		Song randSong = songList.get(new Random().nextInt(songList.size()));
		System.out.println(randSong);
		when(songDAO.findById(randSong.getId())).thenReturn(Optional.of(randSong));
		SongDTO songDTO = songService.getOneById(randSong.getId());
		assertNotNull(songDTO);
		assertEquals(randSong.getId(), songDTO.getId());
		assertEquals(randSong.getName(), songDTO.getName());
		assertEquals(randSong.getArtists(), songDTO.getArtistName());
	}
}
