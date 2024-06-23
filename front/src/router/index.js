import { createRouter, createWebHistory } from 'vue-router'
import NeprijavljeniHomePage from '../views/NeprijavljeniKorisnikHomePageView.vue';
import ProizvodDetalji from "@/views/ProizvodDetalji.vue";
import LoginPageView  from "@/views/LoginPageView.vue";
import RegisterPageView from "@/views/RegisterPageView.vue";
import KupacHomeView from "@/views/KupacHomeView.vue";
import ProizvodDetaljiKupacView from "@/views/ProizvodDetaljiKupacView.vue";
import KupacUpdateInfoView from  "@/views/KupacUpdateInfoView.vue";
import PrikazInformacijaKupca from "@/views/PrikazInformacijaKupca.vue";
import PregledRecenzijaKupac from "@/views/PregledRecenzijaKupac.vue";
import PrijavaProfilaKupac from "@/views/PrijavaProfilaKupac.vue";
const routes = [
    {
        path:'/',
        redirect:'/home',
    },
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
    },
    {
        path:'/korisnik/logged/kupac',
        name:'KupacHomeView',
        component: KupacHomeView
    },
    {
        path:'/korisnik/logged/kupac/proizvod-detalji/:id',
        name:'ProizvodDetaljiKupacView',
        component: ProizvodDetaljiKupacView
    },
    {
        path:'/korisnik/logged/kupac/updateinfo',
        name:'KupacUpdateInfoView',
        component:KupacUpdateInfoView,
    },{
    path:'/korisnik/kupac/pregled/:id',
        name:'PrikazInformacijaKupca',
        component: PrikazInformacijaKupca,
    },
    {
        path:'/korisnik/kupac/pregledRecenzija',
        name: 'PregledRecenzijaKupac',
        component: PregledRecenzijaKupac,
    },
    {
        path:'/prijavaprofila/podnosiprijavu/kupac',
        name:'PrijavaProfilaKupac',
        component: PrijavaProfilaKupac,
    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})
export default router