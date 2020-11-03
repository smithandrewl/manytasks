<template>
  <v-card>
    <v-card-title>Create Task</v-card-title>
    <v-container>
      <v-row>
        <v-col cols ="12">
        <v-text-field label="Title"></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12">
          <v-textarea label="Description"></v-textarea>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="1">
          <v-btn color="primary">Add</v-btn>
        </v-col>
        <v-col cols="1">
          <v-btn to="/home">Cancel</v-btn>
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
