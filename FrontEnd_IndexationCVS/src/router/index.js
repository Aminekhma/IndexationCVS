import Vue from 'vue'
import VueRouter from 'vue-router'
import dashboard from '../views/Dashboard'

Vue.use(VueRouter)

window.axios = require('axios');


const routes = [
  {
    path: '',
    component: dashboard,
    children: [
      {
        path: '',
        component: () => import(/* webpackChunkName: "Overview" */ '../views/Search.vue')
      },
      {
        path: 'allprofil',
        component: () => import(/* webpackChunkName: "Messages" */ '../views/Allprofil.vue')
      },
      {
        path: 'AddCV',
        component: () => import(/* webpackChunkName: "Profile" */ '../views/AddCV.vue')
      },
      {
        path: 'settings',
        component: () => import(/* webpackChunkName: "Settings" */ '../views/Settings.vue')
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router

