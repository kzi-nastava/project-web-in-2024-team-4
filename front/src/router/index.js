import { createRouter, createWebHistory } from 'vue-router'
import NeprijavljeniHomePage from '../views/NeprijavljeniKorisnikHomePageView.vue';
import ProizvodDetalji from "@/views/ProizvodDetalji.vue";
import LoginPageView  from "@/views/LoginPageView.vue";
import RegisterPageView from "@/views/RegisterPageView.vue";
const routes = [
    {
        path: '/home',
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
    path: '/korisnik/prijava-korisnika',
        name:'LoginPageView',
        component: LoginPageView
    },
    {
        path: '/korisnik/registracija',
        name: 'RegisterPageView',
        component: RegisterPageView
    }


]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router