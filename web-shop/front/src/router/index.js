import { createRouter, createWebHistory } from 'vue-router'
import NeprijavljeniHomePage from '../views/NeprijavljeniKorisnikHomePageView.vue';
import ProizvodDetalji from "@/views/ProizvodDetalji.vue";
import ProfileUpdate from "@/views/ProfileUpdate.vue";
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
    },

    {
         path: '/updateProfile',
         name : '/UpdateProfile',
         component: ProfileUpdate,
    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router