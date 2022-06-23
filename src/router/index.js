import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    name: 'Login',
    meta: { requireAuth: true },
    component: () => import('../views/LoginView.vue'),
  },
  {
    path: '/indexView',
    name: 'Index',
    meta: { requireAuth: true },
    component: () => import('../views/IndexView.vue'),
    redirect:'/indexView/main',
    children:[
      {
        path: 'main',
        name: 'Main',
        meta: { requireAuth: true ,comp:'Main',name:"主页"},
        component: () => import('../views/MainView.vue'),
      },
      {
        path: 'repairInfoListView',
        name: 'RepairInfoListView',
        meta: { requireAuth: true,comp:'RepairInfoListView',name:"报修信息" },
        component: () => import('../views/RepairInfoListView.vue'),
      },
      {
        path: 'sysUserListView',
        name: 'SysUserListView',
        meta: { requireAuth: true ,comp:'SysUserListView',name:"用户信息"},
        component: () => import('../views/SysUserListView.vue'),
      },
      {
        path: 'wxUserListView',
        name: 'WxUserListView',
        meta: { requireAuth: true,comp:'WxUserListView',name:"微信用户信息" },
        component: () => import('../views/WxUserListView.vue'),
      },
    ]
   
  },
  

 
  {
    path: '/:catchAll(.*)',
    name: 'NoFound',
    component: () => import('../views/NoFoundView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes: routes
})


router.beforeEach((to, from, next) => {

  if (to.meta.title) {
    document.title = to.meta.title
  }
  // 判断该路由是否需要登录权限
  if (to.meta.requireAuth) {
    if (localStorage.getItem('token')) {
      //需调用接口判断token是否有效，无效则需重新登录。
      if (to.name == "Login") {
        next("/indexView");
      } else {
        next();
      }
    } else {
      //跳转到登录页面必须先判断是否是login，是则next不是则next("/login")
      if (to.name == "Login") {
        next();
      } else {
        next("/");
      }
    }
  } else {
    next()
  }
})
export default router
