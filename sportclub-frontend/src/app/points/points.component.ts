import { Component, OnInit } from '@angular/core';
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'points' })
@Component({
  selector: 'app-points',
  templateUrl: './points.component.html',
  styleUrls: ['./points.component.css']
})
export class PointsComponent implements OnInit {
  searchTerm:string;
  pageTitle:string = 'Points List';
  subTotal:number=0;
  points:any[]=[
    {
      "rankingId":2,
      "pointName":"marc",
      "totalPoint":250
    },
    {
      "rankingId":1,
      "pointName":"jill",
      "totalPoint":400
    },
    {
      "rankingId":1,
      "pointName":"sam",
      "totalPoint":750
    }
  ];

  ngOnInit(): void {
  }

  sortedPoints:any[]=this.points.sort(function(obj1, obj2) {
      return obj2.totalPoint - obj1.totalPoint;
    });

  getMyPoints(){

  }
  getTotalSum():number{
    for (let pt of this.points) {
      this.subTotal+=pt.totalPoint;
    }
    return this.subTotal;
  }
  getSearch():any{
    let term = this.searchTerm;
    for(let item of this.sortedPoints){
      if(item.name.contains(this.searchTerm)){

      }
    }
  }
  transform(points: any, searchTerm: any): any {
    if(searchTerm == null) return points;

    return points.filter(function(name){
      return name.CategoryName.toLowerCase().indexOf(searchTerm.toLowerCase()) > -1;
    })
  }
}
