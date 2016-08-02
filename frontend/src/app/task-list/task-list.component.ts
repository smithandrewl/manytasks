import { Component, OnInit } from '@angular/core';
import {SiteHeadingComponent} from "../site-heading/site-heading.component";
import {DataService} from "../data-service.service";
import { Response} from '@angular/http';

@Component({
  moduleId: module.id,
  selector: 'app-task-list',
  templateUrl: 'task-list.component.html',
  styleUrls: ['task-list.component.css'],
  directives: [SiteHeadingComponent],
  providers: [DataService]
})
export class TaskListComponent implements OnInit {
  private taskData: any;

  constructor(private dataService: DataService) { }

  ngOnInit() {
    this.dataService.getTasks().subscribe(this.onRefresh);
  }

  private onRefresh = (resp: Response) => {
    this.taskData = [];

    this.taskData = resp.json();
  }

}
