package com.radiolossantos.songsservice.service;

import java.util.List;

import com.radiolossantos.songsservice.dto.SongDTO;
import com.radiolossantos.songsservice.entity.Song;

public interface SongService {
	
	public SongDTO getOneById(String id);
	public List<SongDTO> getAll();
	public void save(List<Song> songs);

}
