import Vue from 'vue'
import VueRouter, { RouteConfig } from 'vue-router'
import listTasks from "@/components/listTasks.vue";
import login from "@/components/login.vue";
import home from "@/components/home.vue";

import createTask from "@/components/create-task.vue";
import adminDashboard from "@/components/admin-dashboard.vue";
import addUser from "@/components/add-user.vue";

Vue.use(VueRouter)

const routes: any = [
  { path: '/', component: login},
  { path: '/home', component: home},
  { path: '/createTask', component: createTask},
  {path: '/listTasks', component: listTasks},
  {path: '/adminDashboard', component: adminDashboard},
  {path: '/addUser', component: addUser}
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
