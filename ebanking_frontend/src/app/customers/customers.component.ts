import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-customers',
  imports: [NgIf],
  templateUrl: './customers.component.html',
  styleUrl: './customers.component.css'
})
export class CustomersComponent implements OnInit {

  customers : any ;
  constructor(private http: HttpClient) {
  }

  ngOnInit() {
    this.http.get("http://localhost:8040/customers").subscribe(data => {
        this.customers = data;
    } , error =>{
      console.log(error);
    })
  }

}
