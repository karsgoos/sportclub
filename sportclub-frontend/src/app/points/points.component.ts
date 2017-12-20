import {Component, OnInit} from '@angular/core';
import {Pipe} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AuthenticationService} from '../login/services/authentication.service';

// @Pipe({ name: 'totalPoints' })
@Component({
  selector: 'app-points',
  templateUrl: './points.component.html',
  styleUrls: ['./points.component.css']
})
export class PointsComponent implements OnInit {
  searchTerm: string;
  pageTitle: string = 'Punten';
  subTotal: number = 0;
  points: any[] = [];

  constructor(private _http: HttpClient, public authService: AuthenticationService) {
  }

  ngOnInit(): void {
    this._http.get('http://localhost:8080/api/points').subscribe((points: any[]) => this.updatePoints(points));
  }

  sortedPoints: any[] = [];
  filteredSortedPoints: any[];

  getMyPoints(): number {
    return this.authService.getCurrentUser().totalPoints;
  }

  updatePoints(points: any[]) {
    this.points = points;

    this.sortedPoints = this.points.sort(function (obj1, obj2) {
      return obj2.points - obj1.points;
    });
    this.filteredSortedPoints = this.sortedPoints;
    this.getTotalSum();
  }

  getTotalSum() {
    this.subTotal = 0;
    for (let pt of this.points) {
      this.subTotal += pt.points;
    }
  }

  getSearch(): any {
    let term = this.searchTerm;
    this.filteredSortedPoints = [];
    for (let item of this.sortedPoints) {
      if (item.fullName.toLowerCase().indexOf(this.searchTerm.toLowerCase()) > -1) {
        //console.log("Pushed "+item.fullName);
        this.filteredSortedPoints.push(item);
      }
    }
  }

  // transform(totalPoints: any, searchTerm: any): any {
  //   if(searchTerm == null) return totalPoints;
  //
  //   return totalPoints.filter(function(name){
  //     return name.CategoryName.toLowerCase().indexOf(searchTerm.toLowerCase()) > -1;
  //   })
  // }
}
