import { createRouter, createWebHistory } from 'vue-router'
import Layout from '@/client/layouts/Layout.vue'

import TrangChu from '@/client/views/TrangChu.vue'
import Giay from "@/client/views/Giay.vue";

const routes = [
    {
        path: '/',
        component: Layout,
        children: [
            { path: '', component: TrangChu },
            { path: 'giay', component: Giay },
            { path: 'giay/:id', component: Giay },
        ]
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
