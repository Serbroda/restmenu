import {Component, HostBinding, ViewChild} from '@angular/core';
import { Plugins } from '@capacitor/core';
import {MatSidenav} from '@angular/material/sidenav';
import {HttpClient} from "@angular/common/http";
import { GreetingService } from 'restmenu-server-sdk-angular';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  @ViewChild('sidenav') sidenav: MatSidenav;
  @HostBinding('class') className = '';
  title = 'restmenu';

  constructor() {

  }

}
