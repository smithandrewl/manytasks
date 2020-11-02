<template>
  <v-card>
    <v-card-title>List Tasks</v-card-title>


      <v-container>
        <v-row>
          <v-col cols="12">
            <v-card>
              <v-card-title>Tasks</v-card-title>

              <v-simple-table>
                <thead>
                  <tr>
                    <th>UserId</th>
                    <th>Title</th>
                    <th>Description</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>task.UserId</td>
                    <td>task.Title</td>
                    <td>task.Description</td>
                  </tr>
                </tbody>
              </v-simple-table>
            </v-card>
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
