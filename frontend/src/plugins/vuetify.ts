import Vue from 'vue';
import Vuetify from 'vuetify/lib';

Vue.use(Vuetify);

export default new Vuetify({
  theme: {
      options: {
        customProperties: true,
      },

      themes: {
        light: {
          primary: '#337AB7',
          accent: '#ACFD03',
          secondary: '#0A4080',
          success: '#13D81B',
          info: '#BCE8F1',
          warning: '#E38D13',
          error: '#B92C28'
        },
    },
  },
});
