import { Component, OnInit } from '@angular/core';
import {DefaultService} from 'restmenu-sdk-angular'

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit {

  constructor(private defaultService: DefaultService) { }

  ngOnInit(): void {
  }

  sendGreeting(): void {
    this.defaultService.createRestaurant({name: 'Test'}).subscribe((res) => console.log(res));
  }
}
