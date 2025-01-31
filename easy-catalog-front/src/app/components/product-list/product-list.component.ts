import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product.model';
import { Category } from '../../models/category.model';
import { CategoryService } from '../../services/category.service';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: `./product-list.html`,
  styleUrls: [`./product-list.css`]
})
export class ProductListComponent implements OnInit {
  products: Product[] = [];
  filteredProducts: Product[] = [];
  searchTerm = '';
  categoryFilter = '';
  showAvailableOnly = false;
  sortField = 'name';
  sortDirection = 1;
  showAddForm = false;
  editingProduct: Product | null = null;
  currentProduct: Product = this.getEmptyProduct();
  selectedId: number | null = null;
  categoriesList: Category[] = [];

  constructor(private productService: ProductService, private categoryService: CategoryService) { }

  ngOnInit() {
    this.productService.getProducts().subscribe(products => {
      this.products = products;
      this.applyFilters();
    });
    this.categoryService.getCategories().subscribe(categories => {
      this.categoriesList = categories;
    });

  }

  refreshProducts() {
    this.productService.getProducts().subscribe(products => {
      this.products = products;
      this.applyFilters();
    });
  }

  getEmptyProduct(): Product {
    return {
      id: 0,
      name: '',
      description: '',
      price: 0,
      category: '',
      available: true
    };
  }

  applyFilters() {
    console.log("this.categoryFilter ", this.categoryFilter)
    this.filteredProducts = this.products.filter(product => {
      const matchesSearch = product.name.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
        product.description.toLowerCase().includes(this.searchTerm.toLowerCase());
      const matchesCategory = !this.categoryFilter || product.category === this.categoryFilter;
      const matchesAvailability = !this.showAvailableOnly || product.available;

      return matchesSearch && matchesCategory && matchesAvailability;
    });
    this.sort(this.sortField);
  }

  sort(field: string) {
    this.sortDirection = this.sortField === field ? -this.sortDirection : 1;
    this.sortField = field;

    this.filteredProducts.sort((a: any, b: any) => {
      if (a[field] < b[field]) return -this.sortDirection;
      if (a[field] > b[field]) return this.sortDirection;
      return 0;
    });
  }

  editProduct(product: Product) {
    this.editingProduct = product;
    this.currentProduct = { ...product };
    this.showAddForm = false;
  }

  deleteProduct(id: number) {
    if (confirm('Are you sure you want to delete this product?')) {
      this.productService.deleteProduct(id).subscribe(() => {
        this.refreshProducts();
      });
    }
  }

  saveProduct() {
    if (this.editingProduct) {
      this.productService.updateProduct(this.currentProduct).subscribe(() => {
        this.refreshProducts();
      });
    } else {
      this.productService.addProduct(this.currentProduct).subscribe(() => {
        this.refreshProducts();
      });
    }
    this.cancelEdit();
  }

  cancelEdit() {
    this.editingProduct = null;
    this.showAddForm = false;
    this.currentProduct = this.getEmptyProduct();
  }

  onSelectChange(event: Event): void {
    const selectElement = event.target as HTMLSelectElement;
    if (selectElement.value) {
      this.currentProduct.category = Number(selectElement.value);
    }
  }
}