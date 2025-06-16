import { createRouter, createWebHistory } from 'vue-router'
import Layout from '@/client/layouts/Layout.vue'

import Home from '@/client/views/Home.vue'

const routes = [
    {
        path: '/',
        component: Layout,
        children: [
            { path: '', name: 'Home', component: Home },
        ]
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
