
import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { timeout } from 'rxjs';

import { BillService } from '../../services/bill';
import { Bill } from '../../models/bill';

@Component({
  selector: 'app-bills',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './bills.html',
  styleUrl: './bills.css'
})
export class Bills implements OnInit {

  private billService = inject(BillService);

  bills = signal<Bill[]>([]);
  loading = signal<boolean>(true);
  errorMessage = signal<string>('');

  ngOnInit(): void {
    this.loadBills();
  }

  loadBills(): void {
    this.loading.set(true);
    this.errorMessage.set('');

    this.billService.getBills()
      .pipe(timeout(10000))
      .subscribe({
        next: (data) => {
          console.log('Factures recues :', data);
          this.bills.set(data);
          this.loading.set(false);
        },
        error: (error) => {
          console.error('Erreur HTTP :', error);
          this.errorMessage.set(
            'Impossible de charger les factures.'
          );
          this.loading.set(false);
        }
      });
  }

  calculateTotal(bill: Bill): number {
    return (bill.productItems ?? []).reduce(
      (total, item) => total + item.price * item.quantity,
      0
    );
  }
}
