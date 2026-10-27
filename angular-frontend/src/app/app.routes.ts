
import { Routes } from '@angular/router';

import { Customers } from './components/customers/customers';
import { Products } from './components/products/products';
import { Bills } from './components/bills/bills';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'customers',
    pathMatch: 'full'
  },
  {
    path: 'customers',
    component: Customers
  },
  {
    path: 'products',
    component: Products
  },
  {
    path: 'bills',
    component: Bills
  },
  {
    path: '**',
    redirectTo: 'customers'
  }
];
