import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { Category } from "../models/category.model";
import { environment } from "../../../environment";
import { AuthService } from "./auth.service";

@Injectable({
  providedIn: "root",
})
export class CategoryService {

  private readonly apiUrl = `${environment.apiUrl}/categories`;

  constructor(private http: HttpClient, private authService: AuthService) { }

  getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(this.apiUrl, { headers: this.authService.getAuthHeaders() });
  }
}
