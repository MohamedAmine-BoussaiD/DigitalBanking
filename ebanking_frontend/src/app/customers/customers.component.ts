import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AsyncPipe, JsonPipe, NgForOf, NgIf} from '@angular/common';
import {CustomerService} from '../services/customer.service';
import {catchError, Observable, of, throwError} from 'rxjs';
import {Customer} from '../model/customer.model';
import {FormBuilder, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-customers',
  imports: [NgIf, JsonPipe, NgForOf, AsyncPipe, ReactiveFormsModule, RouterLink],
  templateUrl: './customers.component.html',
  styleUrl: './customers.component.css'
})
export class CustomersComponent implements OnInit {

  customers! : Observable<Array<Customer>> ;
  errorMessage!: string;
  searchFormGroup!: FormGroup;

  constructor(private customerService : CustomerService , private fb:FormBuilder) {
  }

  ngOnInit() {

    this.searchFormGroup = this.fb.group({
      keyword : this.fb.control("")
    });

    this.customers = this.customerService.getCustomers().pipe(
      catchError( err=> {
        this.errorMessage = err.message;
        return throwError(err);
      })
    );
  }

  protected handleSearchCustomers() {

    let kw = this.searchFormGroup.value.keyword;
    this.customers = this.customerService.searchCustomers(kw).pipe(
      catchError( err => {
        this.errorMessage = err.message;
        return throwError(err);
      })
    )
  }

  protected handleDeleteCustomer(id: number) {
    this.customerService.deleteCustomer(id).subscribe({
      next: ()=> {
        this.handleSearchCustomers();
      },
      error: (err)=> {
        console.log(err)
      }
    })
  }
}
