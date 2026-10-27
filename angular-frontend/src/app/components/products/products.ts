
import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { timeout } from 'rxjs';

import { ProductService } from '../../services/product';
import { Product } from '../../models/product';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './products.html',
  styleUrl: './products.css'
})
export class Products implements OnInit {

  private productService = inject(ProductService);

  products = signal<Product[]>([]);
  loading = signal<boolean>(true);
  errorMessage = signal<string>('');

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(): void {
    this.loading.set(true);
    this.errorMessage.set('');

    this.productService.getProducts()
      .pipe(timeout(10000))
      .subscribe({
        next: (data) => {
          console.log('Produits recus :', data);
          this.products.set(data);
          this.loading.set(false);
        },
        error: (error) => {
          console.error('Erreur HTTP :', error);
          this.errorMessage.set(
            'Impossible de charger les produits.'
          );
          this.loading.set(false);
        }
      });
  }
}
