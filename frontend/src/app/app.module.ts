import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AppSiteHeadingComponent } from './app-site-heading/app-site-heading.component';
import { FrontendAppComponent } from './frontend-app/frontend-app.component';
import { HomeComponent } from './home/home.component';
import { CreateTaskComponent } from './create-task/create-task.component';
import { AdminUserListComponent } from './admin-user-list/admin-user-list.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { EventListComponent } from './event-list/event-list.component';
import { TaskListComponent } from './task-list/task-list.component';

@NgModule({
  declarations: [
    AppComponent,
    AppSiteHeadingComponent,
    FrontendAppComponent,
    HomeComponent,
    CreateTaskComponent,
    AdminUserListComponent,
    AdminDashboardComponent,
    EventListComponent,
    TaskListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
