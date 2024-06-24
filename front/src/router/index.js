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
import AdministratorView from  "@/views/AdministratorView.vue";
import AdministratorObradaPrijava from "@/views/AdministratorObradaPrijava.vue";
import ProdavacHomeView from '@/views/ProdavacHomeView.vue';
import ProdavacUpdateInfoView from "@/views/ProdavacUpdateInfoView.vue";
import ProizvodDetaljiProdavacView from "@/views/ProizvodDetaljiProdavacView.vue";
import PregledSvihProfilaProdavac from "@/views/PregledSvihProfilaProdavac.vue";
import DetaljanPrikazProfilaIzProdavca from "@/views/DetaljanPrikazProfilaIzProdavca.vue";
import DodajProizvodForma from "@/views/DodajProizvodForma.vue";
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
    },
    {
        path:'/korisnik/logged/admin',
        name:'AdministratorView',
        component: AdministratorView,
    },
    {
        path: '/korisnik/logged/admin/prijaveprofila',
        name:'AdministratorObradaPrijava',
        component:AdministratorObradaPrijava
    },
    {
        path: '/korisnik/logged/prodavac',
        name: 'ProdavacHomeView',
        component: ProdavacHomeView,
    },
    {
        path: '/korisnik/logged/prodavac/updateinfo',
        name:'ProdavacUpdateInfoView',
        component: ProdavacUpdateInfoView,
    },{
        path:'/korisnik/logged/prodavac/proizvod-detalji/:id',
        name:'ProizvodDetaljiProdavacView',
        component: ProizvodDetaljiProdavacView,
    },
    {
        path: '/logged/prodavac/pregled',
        name:'PregledSvihProfilaProdavac',
        component: PregledSvihProfilaProdavac
    },
    {
        path:'/logged/prodavac/pregled/:id/:uloga',
        name:'DetaljanPrikazProfilaIzProdavca',
        component: DetaljanPrikazProfilaIzProdavca,
    },
    {
        path: '/logged/prodavac/dodajproizvod',
        name: 'DodajProizvodForma',
        component: DodajProizvodForma,
    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})
export default router