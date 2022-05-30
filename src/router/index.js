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
  },
  {
    path: '/teacher',
    component: homeLayout,
    children: [
      {
        path: 'courseAdmin',
        component: () => import('@/views/teacher/courseAdmin/courseAdmin')
      },
      {
        path: 'gradeAdmin',
        component: () => import('@/views/teacher/gradeAdmin/gradeAdmin')
      }
    ]
  },
  {
    path: '/secretary',
    component: homeLayout,
    children: [
      {
        path: 'classAdmin',
        component: () => import('@/views/secretary/classAdmin/classAdmin')
      },
      {
        path: 'planAdmin',
        component: () => import('@/views/secretary/planAdmin/planAdmin')
      },
      {
        path: 'selectAdmin',
        component: () => import('@/views/secretary/selectAdmin/selectAdmin')
      },
      {
        path: 'studentAdmin',
        component: () => import('@/views/secretary/studentAdmin/studentAdmin')
      }
    ]
  },
  {
    path: '/student',
    component: homeLayout,
    children: [
      {
        path: 'checkCourse',
        component: () => import('@/views/student/checkCourse/checkCourse')
      },
      {
        path: 'checkGrade',
        component: () => import('@/views/student/checkGrade/checkGrade')
      },
      {
        path: 'selectCourse',
        component: () => import('@/views/student/selectCourse/selectCourse')
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