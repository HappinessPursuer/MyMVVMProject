import Vue from 'vue'
import VueRouter from 'vue-router'

import Main from '../views/Main'

import Purchase from '../views/Purchase'
import SPGL from '../views/PurchaseComponents/ShangPinGuanLi'
import DDXT from '../views/PurchaseComponents/DingDanXiTong'
import GYSCX from '../views/PurchaseComponents/GongYingShangChaXun'

import Manage from '../views/Manage'
import DDSP from '../views/ManageComponents/DingDanShenPi'

Vue.use(VueRouter);

const routes = [
  {
    path:'/main',
    name:'Main',
    component:Main,
    children:[
      {
        path: '/purchase',
        name: 'Purchase',
        component: Purchase,
        props:true,
        children:[
          {
            path:'/spgl',
            name:'SPGL',
            component:SPGL
          },
          {
            path:'/ddxt',
            name:'DDXT',
            component:DDXT
          },
          {
            path:'/gyscx',
            name:'GYSCX',
            component:GYSCX
          }
        ]
      },
      {
        path: '/manage',
        name: 'Manage',
        component:Manage,
        props:true,
        children:[
          {
            path:'/ddsp',
            name:'DDSP',
            component:DDSP
          }
        ]
      }
    ]
  },

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
});

//路由导航守卫，执行过滤器的相关工作
//to要访问的页面，from从哪里来访问的，next接下来要做的事
//next(url)表示重定向到一个URL，next()表示执行to
router.beforeEach((to,from,next)=>{
  if(to.path=='/'){
    return next();
  }
  // const userFlag=this.$store.state.loginState;
  // if(!userFlag){
  //   return next('/');
  // }
  next();
});

export default router
