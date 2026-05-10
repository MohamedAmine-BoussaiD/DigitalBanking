import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {Customer} from '../model/customer.model';
import {CustomerService} from '../services/customer.service';
import {NgIf} from '@angular/common';
import {Router} from '@angular/router';

@Component({
  selector: 'app-new-customer',
  imports: [
    ReactiveFormsModule,
    NgIf
  ],
  templateUrl: './new-customer.component.html',
  styleUrl: './new-customer.component.css'
})
export class NewCustomerComponent implements OnInit {

  newCustomerForm! : FormGroup ;
  customer!:Customer;

  constructor(private fb:FormBuilder
              , private customerService:CustomerService
              , private router: Router) {
  }


  ngOnInit() {
    this.newCustomerForm = this.fb.group({
      name : this.fb.control(null , [Validators.required , Validators.minLength(4)]),
      email : this.fb.control(null , [Validators.email , Validators.required])
    });
  }


  protected handleNewCustomer() {
    this.customer = this.newCustomerForm.value;
    this.customerService.newCustomer(this.customer).subscribe({
      next : data =>{
        //this.newCustomerForm.reset();
        this.router.navigateByUrl("/customers");
      },
      error : error => {
        console.log(error);
      }
    });
  }
}
