import { Component, OnInit } from '@angular/core';

@Component({
  moduleId: module.id,
  selector: 'app-app-create-task',
  templateUrl: 'app-create-task.component.html',
  styleUrls: ['app-create-task.component.css']
})
export class AppCreateTaskComponent implements OnInit {
  private ckeditor: any;

  constructor() { }

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

}