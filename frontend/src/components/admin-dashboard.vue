<template>
  <v-card>
    <v-card-title>Admin Dashboard</v-card-title>


    <v-container>
      <v-row>
        <v-col cols="1">
          <v-btn color="success">Add a User</v-btn>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12">
          <v-card>
            <v-card-title>Users</v-card-title>

            <v-simple-table>
              <thead>
              <tr>
                <th>Username</th>
                <th>isAdmin</th>
                <th>Delete</th>
              </tr>
              </thead>
              <tbody>
              <tr>
                <td>Username</td>
                <td>isAdmin</td>
                <td>Delete</td>
              </tr>
              </tbody>
            </v-simple-table>
          </v-card>
        </v-col>
      </v-row>

      <v-row>
        <v-col cols="1">
          <v-btn color="error">Clear All Logs</v-btn>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12">
          <v-card>
            <v-card-title>Events</v-card-title>

            <v-simple-table>
              <thead>
              <tr>
                <th>Timestamp</th>
                <th>User Id</th>
                <th>Event Type</th>
                <th>Section</th>
                <th>Action</th>
                <th>ActionResult</th>
                <th>Event Severity</th>
              </tr>
              </thead>
              <tbody>
              <tr>
                <td>Timestamp</td>
                <td>User Id</td>
                <td>Event Type</td>
                <td>Section</td>
                <td>Action</td>
                <td>ActionResult</td>
                <td>Event Severity</td>
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
