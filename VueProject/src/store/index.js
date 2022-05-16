import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  //相当于组件中的data，但存放的是全局数据
  state: {
    //令loginState的默认值是false，就是默认没有登录
    loginState:false,
    //保存当前登录的user的数据
    userCurrent:[
      {
        idUser:'',
        username:'',
        password:'',
        position:''
      }
    ]
  },
  //
  mutations: {
  },
  actions: {
  },
  modules: {
  }
})
