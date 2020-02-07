import { UserService } from './../../login/user.service';
import { Component, OnInit } from '@angular/core';
import { VoteService } from '../../vote/vote.service';

export interface UsersVote {
  userId: number;
  nickname:string;
  voteCount: number;
}


@Component({
  selector: 'app-users-vote',
  templateUrl: './users-vote.component.html',
  styleUrls: ['./users-vote.component.css']
})
export class UsersVoteComponent implements OnInit {

  constructor(private voteService: VoteService,
              private userService: UserService) { }
  public usersVotes: UsersVote[] = [];
  public displayedColumns: string[] = ['userId', 'nickname', 'voteCount'];
  public userCount: number = 0
  
  ngOnInit() {
    this.loadUsersVote();
  }

  loadUsersVote(){ 
    this.voteService.getUsersVoteCountOnMostVoted().subscribe(
      data =>{
        if(data != undefined && data.length != 0 ){
          this.userCount = data.userVotes.length;
          const tempMostVoted = []
          data.userVotes.forEach(userVote => {
            this.userService.getUser(userVote.userId).subscribe(
              user =>{
                const vote: UsersVote={
                  userId: userVote.userId,
                  nickname: user.nickname,
                  voteCount: userVote.voteCount
                }
                tempMostVoted.push(vote);
              }
            )
          })
          this.usersVotes = tempMostVoted;
          console.log(this.usersVotes);
        }
      }
    )
  }

}
