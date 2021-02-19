import { Component, OnInit } from '@angular/core';
import { GreetingService } from '../../../../../restmenu-server-sdk-angular/target/generated-sources';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit {

  constructor(private greet: GreetingService) { }

  ngOnInit(): void {
  }

  sendGreeting(): void {
    this.greet.getGreeting('Danny').subscribe((res) => {
      console.log(res)
    })
  }
}
