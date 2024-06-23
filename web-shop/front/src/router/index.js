import { createRouter, createWebHistory } from 'vue-router'
import NeprijavljeniHomePage from '../views/NeprijavljeniKorisnikHomePageView.vue';
import ProizvodDetalji from "@/views/ProizvodDetalji.vue";
import LoginPageView  from "@/views/LoginPageView.vue";
import RegisterPageView from "@/views/RegisterPageView.vue";
import ProfileUpdate from "@/views/ProfileUpdate.vue";
import ProfileView from "@/views/ProfileView.vue";
import ProfilesView from "@/views/ProfilesView.vue";
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
        path: '/korisnik/prijava-korisnika',
        name:'LoginPageView',
        component: LoginPageView
    },
    {
        path: '/korisnik/registracija',
        name: 'RegisterPageView',
        component: RegisterPageView
    },

    {
         path: '/updateProfile',
         name : '/ProfileUpdate',
         component: ProfileUpdate
    },

    {
        path: '/prodavac/pregled',
        name : '/ProfileView',
        component: ProfileView
    },

    {
        path: '/prodavac/pregled/:id',
        name: 'ProfilesView',
        component: ProfilesView
    }


]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router