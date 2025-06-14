import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import { createPinia } from 'pinia'
import axios from 'axios'

axios.defaults.baseURL = import.meta.env.VITE_BASE_URL;

const app = createApp(App)
app.config.globalProperties.$axios = axios;
app.use(createPinia())
app.use(router)
app.mount('#app')
