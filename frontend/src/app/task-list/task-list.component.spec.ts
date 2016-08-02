/* tslint:disable:no-unused-variable */

import { By }           from '@angular/platform-browser';
import { DebugElement } from '@angular/core';
import { addProviders, async, inject } from '@angular/core/testing';
import { TaskListComponent } from './task-list.component';

describe('Component: TaskList', () => {
  it('should create an instance', () => {
    let component = new TaskListComponent();
    expect(component).toBeTruthy();
  });
});
