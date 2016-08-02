import { LoginScreenComponent} from './login-screen';
import { AdminDashboardComponent} from './admin-dashboard';
import {HomeComponent} from "./home/home.component";
import {AdminCreateUserComponent} from "./admin-create-user/admin-create-user.component";
import {AppCreateTaskComponent} from "./app-create-task/app-create-task.component";
import {TaskListComponent} from "./task-list/task-list.component";

export const AppRoutes = [
    { path: '', component: LoginScreenComponent },
    { path: 'admin', component: AdminDashboardComponent },
    { path: 'home', component: HomeComponent },
    { path: 'create-user', component: AdminCreateUserComponent},
    { path: 'createTask', component: AppCreateTaskComponent},
    { path: 'listTasks', component: TaskListComponent}
];