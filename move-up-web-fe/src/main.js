import { createApp } from 'vue'
import App from './App.vue'
import clientRouter from './client/router.js'
import { createPinia } from 'pinia'
import axios from 'axios'
import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import {createBootstrap} from 'bootstrap-vue-next'
import { fas } from '@fortawesome/free-solid-svg-icons'
import { far } from '@fortawesome/free-regular-svg-icons'
import { fab } from '@fortawesome/free-brands-svg-icons'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue-next/dist/bootstrap-vue-next.css'

library.add(fas, far, fab)

axios.defaults.baseURL = import.meta.env.VITE_BASE_URL;

const app = createApp(App)
app.component('font-awesome-icon', FontAwesomeIcon)
app.config.globalProperties.$axios = axios;
app.use(createBootstrap())
app.use(createPinia())
app.use(clientRouter)
app.mount('#app')
