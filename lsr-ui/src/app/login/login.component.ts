import { UserService } from './user.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder} from "@angular/forms";
import { Router } from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public loginForm: FormGroup;

  constructor(private formBuilder: FormBuilder,
    private userService: UserService,
    private router: Router) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      nickname: ''
    });
  }

  onSubmit(userData){
    this.userService.login(userData.nickname).pipe().subscribe(
      data =>{
        this.router.navigate(["/vote/" + data.id])
      }
    );
  }

}
