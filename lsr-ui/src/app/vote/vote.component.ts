import { SongService } from './../admin/song.service';
import { VoteService } from './vote.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute} from '@angular/router';
import { FormGroup, FormBuilder} from "@angular/forms";
import { Router } from "@angular/router";

@Component({
  selector: 'app-vote',
  templateUrl: './vote.component.html',
  styleUrls: ['./vote.component.css']
})
export class VoteComponent implements OnInit {
  songList: any[];
  userId: number;
  public voteForm: FormGroup;
  constructor(private route: ActivatedRoute,
          private voteService: VoteService,
          private songService: SongService,
          private formBuilder: FormBuilder,
          private router: Router) { }

  public maxVotes = 5;
  public userVotes = [];
  public disableSubmit = false;

  ngOnInit() {
    this.voteForm = this.formBuilder.group({
      selectedOptions: ''
    });
    this.route.params.subscribe(params => {
      this.loadSongs();
      if(params.id != undefined){
        this.userId = params.id;
        this.loadVotes(this.userId);
      }
    })
  }

  loadSongs(){
    this.songService.loadSongs().subscribe(
      data =>{
        this.songList = data;
        this.songList.sort((a, b) => (a.genre > b.genre ) ? 1: -1);
      }
    );
  }


  loadVotes(userId: number){
    this.voteService.getUserVotes(userId).subscribe(
      data =>{
        this.userVotes = data;
        if(this.userVotes.length > 0 ){
          this.disableSubmit = true;
        }
      }
    )
  }

  onSubmit(votes: any){
    console.log(votes)
    this.voteService.submitVote(this.userId, votes.selectedOptions).subscribe(
      data =>{
        if(data){
          this.router.navigate(["/"]);
        }
      }
    )
  }
  hasBeenPreviouslySelected(songId: string){
    var found = false;
    for(var i = 0; i < this.userVotes.length; i++) {
      if (this.userVotes[i].songId == songId) {
          found = true;
          break;
      }
    }
    return found;
  }
}
