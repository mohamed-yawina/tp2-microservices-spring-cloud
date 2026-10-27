
import { Customer } from './customer';
import { Product } from './product';

export interface ProductItem {
  id: number;
  productId: number;
  quantity: number;
  price: number;
  product?: Product;
}

export interface Bill {
  id: number;
  billingDate: string;
  customerId: number;
  customer?: Customer;
  productItems: ProductItem[];
}
