import { createRouter, createWebHistory } from 'vue-router'
import NeprijavljeniHomePage from '../views/NeprijavljeniKorisnikHomePageView.vue';
import ProizvodDetalji from "@/views/ProizvodDetalji.vue";
const routes = [
    {
        path: '/',
        name: 'home',
        component: NeprijavljeniHomePage,
        meta:{
            title:'Home'
        }
    },
    {
        path: '/proizvod/lista-proizvoda/:id',
        name: 'ProizvodDetalji',
        component: ProizvodDetalji
    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router