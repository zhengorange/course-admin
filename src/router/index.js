import Vue from 'vue'
import VueRouter from 'vue-router'
import { loginLayout, homeLayout } from "@/layouts";
import { getToken } from '@/utils'

Vue.use(VueRouter)

const routes = [
  {
    path: "*",
    redirect: "/404"
  },
  {
    path: "/404",
    name: "404",
    component: () => import("@/views/404/404")
  },
  {
    path: '/',
    redirect: "/login",
    component: loginLayout,
    children: [
      {
        path: '/login',
        name: 'login',
        component: () => import('@/views/login/login')
      },
      {
        path: '/register',
        name: 'register',
        component: () => import('@/views/register/register')
      }
    ]
  },
  {
    path: '/home',
    redirect: "/welcome",
    component: homeLayout,
    children: [
      {
        path: '/welcome',
        name: "welcome",
        component: () => import('@/views/welcome/welcome')
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}
router.beforeEach((to, from, next) => {
  const path = to.name
  if (path === "login" || path === "register") {
    if (getToken()) {
      next({ path: '/welcome' })
    } else {
      next()
    }
  } else {
    if (getToken()) {
      next()
    } else {
      next({ path: '/login' })
    }
  }
});

export default router