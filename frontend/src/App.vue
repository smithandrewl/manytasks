<template>
  <v-app>
    <v-main>
      <login/>
      <home/>
      <create-task/>
      <list-tasks/>
      <admin-dashboard/>
      <add-user/>
    </v-main>
  </v-app>
</template>

<script lang="ts">
import Vue from 'vue';
import login from './components/login.vue';
import home from './components/home.vue';
import createTask from './components/create-task.vue';
import listTasks from './components/listTasks.vue';
import adminDashboard from './components/admin-dashboard.vue';
import addUser from './components/add-user.vue';

export default Vue.extend({
  name: 'App',

  components: {
    login,
    home,
    createTask,
    listTasks,
    adminDashboard,
    addUser
  },

  data: () => ({
    //
  }),
});
</script>
