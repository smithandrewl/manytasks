<template>
  <v-card>
    <v-card-title>Add User</v-card-title>

    <v-container>
      <v-row>
        <v-col cols="1">
          <v-btn color="primary" to="/adminDashboard">Back</v-btn>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12">
          <v-text-field label="Username"></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12">
          <v-text-field label="Password"></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="1">
          <v-checkbox label="isAdmin"></v-checkbox>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12" >
          <v-btn
              block
              color="primary"
          >Add</v-btn>
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
