const routes = [
  {
    path: '/',
    component: () => import('layouts/guest.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/login.vue')
      }
    ]
  },
  {
    path: '/register',
    component: () => import('layouts/guest.vue'),
    children: [
      { path: '', component: () => import('pages/register.vue') }
    ]
  },
  {
    path: '//index',
    component: () => import('layouts/Mylayout.vue'),
    children: [
      { path: '', component: () => import('pages/index.vue') }
    ]
  },
  {
    path: '/lihatguru',
    component: () => import('layouts/Mylayout.vue'),
    children: [
      { path: '', component: () => import('pages/lihatguru.vue') }
    ]
  },
  {
    path: '/inputguru',
    component: () => import('layouts/Mylayout.vue'),
    children: [
      { path: '', component: () => import('pages/inputguru.vue') }
    ]
  },
  {
    path: '/inputsiswa',
    component: () => import('layouts/Mylayout.vue'),
    children: [
      { path: '', component: () => import('pages/inputsiswa.vue') }
    ]
  },
  {
    path: '/lihatsiswa',
    component: () => import('layouts/Mylayout.vue'),
    children: [
      { path: '', component: () => import('pages/lihatsiswa.vue') }
    ]
  },
  {
    path: '/lihatjadwal',
    component: () => import('layouts/Mylayout.vue'),
    children: [
      { path: '', component: () => import('pages/lihatjadwal.vue') }
    ]
  },
  {
    path: '/inputjadwal',
    component: () => import('layouts/Mylayout.vue'),
    children: [
      { path: '', component: () => import('pages/inputjadwal.vue') }
    ]
  },
  {
    path: '/inputspp',
    component: () => import('layouts/Mylayout.vue'),
    children: [
      { path: '', component: () => import('pages/inputspp.vue') }
    ]
  },
  {
    path: '/lihatspp',
    component: () => import('layouts/Mylayout.vue'),
    children: [
      { path: '', component: () => import('pages/lihatspp.vue') }
    ]
  }
]
// Always leave this as last one
if (process.env.MODE !== 'ssr') {
  routes.push({
    path: '*',
    component: () => import('pages/Error404.vue')
  })
}

export default routes
