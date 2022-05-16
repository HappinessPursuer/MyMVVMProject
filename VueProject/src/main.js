import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import store from './store'

import axios from 'axios'
Vue.prototype.$http=axios;
axios.defaults.baseURL='http://localhost:8181';

Vue.config.productionTip = false;

window.sessionStorage.setItem("loginState",false);

new Vue({
  store,
  router,
  render: h => h(App)
}).$mount('#app');
