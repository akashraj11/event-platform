import { Component, OnInit, Input } from '@angular/core';
import { Producer } from 'src/app/domain/producer';
import { HttpClient } from "@angular/common/http";
import { environment as env } from "../../../environments/environment";
import { from } from 'rxjs';
import { Theatre } from '../../domain/theatre';
import { ScreenLayout } from '../../domain/screenLayout';
import { Category } from '../../domain/category';
import { ProducerService } from '../../services/producer-service';
import { shouldCallLifecycleInitHook } from '@angular/core/src/view';


@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  private producer: Producer;
  public email="abcfd@gmail.com";
  public theatreId: number;
  public theatreName: string;
  public theatreAddress: string;
  public theatreCity: string;
  public screenLayout: ScreenLayout;
  public category: Category;

  @Input() private some: Theatre;

  theatreArr;

  constructor(private http: HttpClient,private producerService: ProducerService) { }

  ngOnInit() {
    this.defaultLoad();
  }

  defaultLoad() {
		console.log("default load started");
		// this.http.get(env.api)
		// 	.subscribe((data) => {
        // console.log("data is ", data);
        // this.producer = {
        //   email: data["email"],
        //   theatres: data['theatres']
        // }
        
        this.theatreId=this.some.theatreId;
        this.theatreName=this.some.theatreName;
        this.theatreCity=this.some.theatreCity;
        this.theatreAddress=this.some.theatreAddress;
        this.screenLayout=this.some.screenLayout;
        this.category=this.some.screenLayout.category[0];
        console.log("layout at card", this.screenLayout);       

  }


  onDelete(theatreNamePassed : string){
    this.producerService.deleteTheatre(this.email,theatreNamePassed);
    window.location.reload(true);
 
  }

}
