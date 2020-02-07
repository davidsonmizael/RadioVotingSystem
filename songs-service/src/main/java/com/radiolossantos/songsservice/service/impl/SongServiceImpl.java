package com.radiolossantos.songsservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radiolossantos.songsservice.dao.SongDAO;
import com.radiolossantos.songsservice.dto.SongDTO;
import com.radiolossantos.songsservice.entity.Song;
import com.radiolossantos.songsservice.service.SongService;

@Service
public class SongServiceImpl implements SongService {
	
	@Autowired
	private SongDAO songDAO;

	@Override
	public List<SongDTO> getAll() {
		List<Song> songList = songDAO.findAll();
		List<SongDTO> songDTOList = null;
		if(!songList.isEmpty()) {
			songDTOList = new ArrayList<>();
			for(Song s: songList) {
				songDTOList.add(SongDTO.builder().name(s.getName())
								.id(s.getId())
								.artistName(s.getArtists())
								.genre(s.getGenre())
								.build());
			}
		}
		return songDTOList;
	}

	@Override
	public void save(List<Song> songs) {
		songDAO.saveAll(songs);
	}

	@Override
	public SongDTO getOneById(String id) {
		Song song = songDAO.findById(id).orElse(null);
		if(null != song) {
			return SongDTO.builder().name(song.getName())
					.id(song.getId())
					.artistName(song.getArtists())
					.genre(song.getGenre())
					.build();
		}
		return null;
	}

}
