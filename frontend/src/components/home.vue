<template>
  <v-card>
    <v-card-title>Home</v-card-title>

    <v-container>
      <v-row>
        <v-col cols="12">
    <v-btn color="primary">Logout</v-btn>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12">
    <v-btn color="primary">Create Task</v-btn>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12">
    <v-btn color="primary">List Tasks</v-btn>
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
