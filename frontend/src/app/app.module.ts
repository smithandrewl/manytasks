import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AppSiteHeadingComponent } from './app-site-heading/app-site-heading.component';
import { FrontendAppComponent } from './frontend-app/frontend-app.component';

@NgModule({
  declarations: [
    AppComponent,
    AppSiteHeadingComponent,
    FrontendAppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
