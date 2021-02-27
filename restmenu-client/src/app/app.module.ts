import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { HomePageComponent } from './pages/home-page/home-page.component';
import {HttpClientModule} from '@angular/common/http';
import {BASE_PATH} from 'restmenu-server-sdk-angular';

import { environment } from '../environments/environment';

@NgModule({
  declarations: [AppComponent, HomePageComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatToolbarModule,
    MatIconModule,
    MatSidenavModule,
    MatListModule,
    HttpClientModule
  ],
  providers: [{provide: BASE_PATH, useValue: environment.apiBaseUrl}],
  bootstrap: [AppComponent],
})
export class AppModule {}
