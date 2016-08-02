import { Component, OnInit } from '@angular/core';
import {RoutingService} from "../routing.service";
import {SiteHeadingComponent} from "../site-heading/site-heading.component";

@Component({
  moduleId: module.id,
  selector: 'app-app-create-task',
  templateUrl: 'app-create-task.component.html',
  styleUrls: ['app-create-task.component.css'],
  directives: [SiteHeadingComponent]
})
export class AppCreateTaskComponent implements OnInit {
  private ckeditor: any;

  constructor(private routingService: RoutingService) { }

  ngOnInit() {

    this.ckeditor = window['CKEDITOR'];

    // Source for ckeditor display fix
    // User: JS_astronauts
    // URL: http://stackoverflow.com/a/37912191
    window['CKEDITOR']['replace']('descriptionEditor');
  }

  add() {
    //var description: string = this.ckeditor.instances.descriptionEditor.getData();
  }

  cancel() {
    this.routingService.changeRoute('/home');
  }

}