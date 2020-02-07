import { Component, OnInit } from '@angular/core';
import { SongService } from '../song.service';
import { VoteService } from '../../vote/vote.service';

export interface TopSong {
  name: string;
  artistName: string;
  genre: string;
  voteCount: number;
}

@Component({
  selector: 'app-most-voted',
  templateUrl: './most-voted.component.html',
  styleUrls: ['./most-voted.component.css']
})
export class MostVotedComponent implements OnInit {

  constructor(private voteService: VoteService,
    private songService: SongService) { }

  private rankingAmount = 5;
  public mostVoted: TopSong[] = [];
  public displayedColumns: string[] = ['name', 'artistName', 'genre', 'voteCount'];

  ngOnInit() {
    this.loadTopMostVoted();
  }

  loadTopMostVoted(){
    this.voteService.getMostVotedByAmount(this.rankingAmount).subscribe(
      data =>{
        if(data != undefined && data.length != 0 ){
          const songList = [];
          data.songs.forEach(song => {
            this.songService.getSong(song.songId).subscribe(
              songData =>{
                const topSong: TopSong={
                  name: songData.name,
                  artistName: songData.artistName,
                  genre: songData.genre,
                  voteCount: song.voteCount
                }
                songList.push(topSong);
              }
            )
          });
          this.mostVoted = songList;
        }
      }
    )
  }
}
