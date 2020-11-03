<template>
  <v-card>
    <v-card-title>Home</v-card-title>

    <v-container>
      <v-row>
        <v-col cols="12">
    <v-btn color="primary" to="/">Logout</v-btn>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12">
    <v-btn color="primary" to="/createTask">Create Task</v-btn>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12">
    <v-btn color="primary" to="/listTasks">List Tasks</v-btn>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12">
          <v-btn color="primary" to="/adminDashboard">Admin Dashboard</v-btn>
        </v-col>
      </v-row>
    </v-container>
  </v-card>
</template>

<script lang="ts">

export default {
  props: {
  },
  computed: {
  }
}
</script>
