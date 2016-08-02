import { Component, OnInit } from '@angular/core';
import {RoutingService} from "../routing.service";
import {LoginCheck} from "../login-check.directive";
import {AuthenticationService} from "../authentication.service";
import {SiteHeadingComponent} from "../site-heading/site-heading.component";
import {AppCreateTaskComponent} from "../app-create-task/app-create-task.component";

@Component({
  moduleId: module.id,
  selector: 'app-home',
  templateUrl: 'home.component.html',
  styleUrls: ['home.component.css'],
  directives: [LoginCheck, SiteHeadingComponent, AppCreateTaskComponent],
  providers: [AuthenticationService]

})
export class HomeComponent implements OnInit {

  constructor(private routingService: RoutingService) {}

  ngOnInit() {
  }
  clicked() {
    window.localStorage.removeItem('jwt');
    this.routingService.changeRoute('');
  }

  createTask() {
    this.routingService.changeRoute('/createTask');
  }

}
