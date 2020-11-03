<template>
<v-card>
  <v-card-title>Please sign in</v-card-title>
  <v-form>
    <v-container>
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
        <v-col cols="12">
        <v-btn color="primary" to="/home">Sign In</v-btn>
        </v-col>
      </v-row>
    </v-container>
  </v-form>
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
