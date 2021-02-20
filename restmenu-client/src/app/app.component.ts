import {Component, HostBinding, ViewChild} from '@angular/core';
import { Plugins } from '@capacitor/core';
import {MatSidenav} from '@angular/material/sidenav';

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
