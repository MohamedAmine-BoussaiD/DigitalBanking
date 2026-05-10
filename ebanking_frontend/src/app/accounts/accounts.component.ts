import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {NgForOf} from '@angular/common';

@Component({
  selector: 'app-accounts',
  imports: [
    NgForOf
  ],
  templateUrl: './accounts.component.html',
  styleUrl: './accounts.component.css'
})
export class AccountsComponent implements OnInit {

  accounts: any;
  constructor(private http : HttpClient) {
  }

  ngOnInit() {
    this.http.get("http://localhost:8040/accounts").subscribe({
      next: data =>{
        this.accounts = data
      },
      error: error => {
        console.log(error);
      }
    });
  }

}
