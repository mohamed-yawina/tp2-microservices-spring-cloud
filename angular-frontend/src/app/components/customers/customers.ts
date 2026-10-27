
import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { timeout } from 'rxjs';

import { CustomerService } from '../../services/customer';
import { Customer } from '../../models/customer';

@Component({
  selector: 'app-customers',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './customers.html',
  styleUrl: './customers.css'
})
export class Customers implements OnInit {

  private customerService = inject(CustomerService);

  customers = signal<Customer[]>([]);
  loading = signal<boolean>(true);
  errorMessage = signal<string>('');

  ngOnInit(): void {
    this.loadCustomers();
  }

  loadCustomers(): void {
    this.loading.set(true);
    this.errorMessage.set('');

    this.customerService.getCustomers()
      .pipe(timeout(10000))
      .subscribe({
        next: (data) => {
          console.log('Clients recus :', data);

          this.customers.set(data);
          this.loading.set(false);
        },
        error: (error) => {
          console.error('Erreur HTTP :', error);

          this.errorMessage.set(
            'Impossible de charger les clients.'
          );

          this.loading.set(false);
        }
      });
  }
}
