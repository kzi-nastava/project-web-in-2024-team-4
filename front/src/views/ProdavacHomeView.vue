<script>
import axios from 'axios'
import router from "@/router";
export default {
  name: "ProdavacHomeView",
  data() {
    return {
      proizvodi: [],
      searchTerm:'',
      searchOpis:'',
      proizvod: [],
      ID:'',
      kategorije:[],
      maxCena:'',
      minCena:'',
      kategorijaPick:'',
      tipProdaje:'',
      message:'',
      korisnik:'',
      page:0,
      sizePage:4,
      hasNextPage:null,
    };
  },
  mounted() {
    this.defaultProizvodi();
    this.getProizvodi(this.page);
    this.searchProizvodi();
    this.getKategorije();
    this.logged();
    this.getKorisnik();
  },
  methods: {
    getProizvodi(page) {
      axios.get(`http://localhost:8081/proizvod/lista-proizvoda`, {params:{page:page,size:this.sizePage},withCredentials: true})
          .then((response) => {
            console.log(response.data);
            if (response.status === 204 || response.data.length === 0) {
              this.proizvodi = [];
              this.hasNextPage = false;
            }
            else {
              this.proizvodi = response.data;
              this.page = page;
              this.hasNextPage = response.data.length === this.sizePage;
            }
          })
          .catch((error) => {
            console.log(error);
            alert(error.message);
          });
    },defaultProizvodi() {
      this.getProizvodi(this.page);
    },
    searchProizvodi(){
      axios.get(`http://localhost:8081/proizvod/search?opis=${this.searchTerm}&naziv=${this.searchTerm}`,{withCredentials:true}).then((response) => {
        this.proizvodi = response.data;
      })
          .catch((error) => {
            console.log(error);
            alert("Ne posotji proizvod sa tim nazivom");
          });
    },
    getKategorije(){
      axios.get(`http://localhost:8081/kategorija`,{withCredentials:true}).then((response) => {
        this.kategorije = response.data;
      })
          .catch((error) => {
            console.log(error);
            alert(error);
          });
    },
    filter() {
      // Provera i postavljanje podrazumevanih vrednosti
      const minCena = this.minCena === "" ? 0 : this.minCena;
      const maxCena = this.maxCena === "" ? 99999999999 : this.maxCena;
      const kategorijaPick = this.kategorijaPick === "" ? "" : this.kategorijaPick;
      const tipProdaje = this.tipProdaje === "" ? null : this.tipProdaje;

      // Slanje GET zahteva ka API-ju sa query parametrima
      axios.get(`http://localhost:8081/proizvod/search/filter`, {
        params: {
          minCena: minCena,
          maxCena: maxCena,
          tipProdaje: tipProdaje,
          nazivKategorije: kategorijaPick
        },
        withCredentials: true
      })
          .then((response) => {
            this.proizvodi = response.data;
          })
          .catch((error) => {
            console.log(error);
            alert("Došlo je do greške prilikom filtriranja proizvoda.");
          });
    },
    logout() {
      axios.post('http://localhost:8081/korisnik/logout', {}, { withCredentials: true })
          .then((response) => {
            this.message = response.data;
            localStorage.removeItem('korisnik');
            router.push('/home');
          })
          .catch((error) => {
            if (error.response && error.response.status === 403) {
              this.message = 'Zabranjeno: Niste prijavljeni';
            } else {
              this.message = 'Došlo je do greške pri odjavi';
            }
          });
    },
    logged(){
      if (localStorage.getItem('korisnik') == null) {
        this.$router.push('/home');
      }
    },
    getKorisnik(){
      this.korisnik=JSON.parse(localStorage.getItem('korisnik'));
    },
    updateinfo(){
      this.$router.push('/korisnik/logged/prodavac/updateinfo')
    },
    pregledrRecenzija(){
      router.push('/korisnik/prodavac/pregledRecenzija');
    },
    podnesiprijavu(){
      router.push('/prijavaprofila/podnosiprijavu/prodavac');
    },
    nextPage(){
      this.currentPage++;
      this.getProizvodi();
      console.log(this.currentPage);
    },
    previousePage(){
      this.currentPage--;
      this.getProizvodi();
      console.log(this.currentPage);
    },
    pregledSvihProfila(){
      this.$router.push('/logged/prodavac/pregled');
    },
    dodajProizvod(){
      this.$router.push('/logged/prodavac/dodajproizvod');
    }
  }
};
</script>

<template>
  <nav  class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
      <a class="navbar-brand" href="/home">WebShop</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="/home">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#" v-on:click="pregledSvihProfila()">Pregled Profila</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#" v-on:click="pregledrRecenzija()">Pregled recenzija prodavca {{this.korisnik.korisnickoIme}}</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#" v-on:click="podnesiprijavu()">Podnesi prijavu</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#" v-on:click="dodajProizvod()">Dodaj Proizvod</a>
          </li>
        </ul>
        <form class="d-flex" role="search" @submit.prevent="searchProizvodi">
          <input class="form-control me-4 search-input" type="search" placeholder="Search" v-model="searchTerm" aria-label="Search">
          <button class="btn btn-outline-success search-button" v-on:click="searchProizvodi()" type="submit">Search</button>
        </form>
        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
          <button v-on:click="logout()" class="btn btn-outline-success">Logout</button>
          <p v-if="message">{{ message }}</p>
          <img :src="korisnik.profilnaSlika" v-on:click="updateinfo()" id="profile-photo">
          <p class="user-name" ><strong>{{korisnik.korisnickoIme}}</strong></p>
        </div>
      </div>
    </div>
  </nav>

  <div class="container mt-5" @submit.prevent="filter">
    <form class="filter_proizvoda">
      <div class="mb-4">
        <label for="minPrice" class="form-label">Najmanja cena:</label>
        <input type="number" class="form-control" id="minPrice" v-model="minCena" name="minPrice" placeholder="Najmanja Cena">
      </div>

      <div class="mb-4">
        <label for="maxPrice" class="form-label">Najveca cena:</label>
        <input type="number" class="form-control" id="maxPrice" v-model="maxCena" name="maxPrice" placeholder="Najveca Cena">
      </div>

      <div class="form-group">
        <label for="category">Kategorija</label>
        <select id="category" v-model="kategorijaPick" required>
          <option v-for="kategorija in kategorije" :key="kategorija.id" :value="kategorija.naziv">
            {{kategorija.naziv}}
          </option>
        </select>
      </div>

      <div class="mb-4">
        <label class="form-label">Nacin prodaje:</label>
        <div class="form-check">
          <input class="form-check-input" type="radio" v-model="tipProdaje" name="tipProdaje" id="sellingType1" value="FiksnaCena">
          <label class="form-check-label" for="fiksnaCena">
            Fiksna cena
          </label>
        </div>
        <div class="form-check">
          <input class="form-check-input" type="radio" v-model="tipProdaje" name="tipProdaje" id="sellingType2" value="Aukcija">
          <label class="form-check-label" for="aukcija">
            Aukcija
          </label>
        </div>
      </div>

      <button  class="btn btn-primary">Filtriraj</button>
    </form>
  </div>

  <div><h1 class="proizvod">Proizvodi</h1></div>
  <div class="card-deck">
    <div v-for="proizvod in proizvodi" :key="proizvod.id" class="card" style="width: 18rem;">
      <img :src="proizvod.slika" class="card-img-top" alt="slika">
      <div class="card-body">
        <h5 class="card-title">{{proizvod.ime}}</h5>
        <p class="card-text">{{proizvod.opis}}</p>
        <p class="card-text"><strong>Cena {{proizvod.cena}} RSD</strong></p>
        <router-link :to="{ name: 'ProizvodDetaljiProdavacView', params: { id: proizvod.id } }" class="btn btn-primary">Vidi još</router-link>
      </div>
    </div>
  </div>

  <div>
    <button class="btn btn-primary me-md-2" @click="getProizvodi(page - 1)" :disabled="page <= 0">Prethodna</button>
    <button class="btn btn-primary me-md-2" @click="getProizvodi(page + 1)" :disabled="!hasNextPage">Sledeća</button>
  </div>
  <footer>
    <p style="user-select: none">&copy; {{ new Date().getFullYear() }} - All rights reserved</p>
  </footer>
</template>

<style scoped>
/* General Form Styling */
.d-flex {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 0.5rem; /* Razmak između inputa i dugmeta */
}

/* Styling for the Search Input */
.search-input {
  padding: 0.5rem;
  font-size: 1rem;
  border-radius: 0.25rem;
  border: 1px solid #ccc;
  flex-grow: 1; /* Omogućava inputu da raste kako bi popunio prostor */
}

/* Styling for the Search Button */
.search-button {
  padding: 0.5rem 1rem;
  font-size: 1rem;
  border-radius: 0.25rem;
  border: 1px solid #28a745;
  color: #28a745;
  background-color: transparent;
  cursor: pointer;
  transition: background-color 0.3s, color 0.3s;
}

.search-button:hover {
  background-color: #28a745;
  color: white;
}

#profile-photo{
  width: 20%;
}

.user-name{
  width: 50px;
  padding-top: 25px;
  margin-right: 20px;
}
.proizvod{
  padding-top: 15px;
}
.navbar {
  background-color: #74EBD5;
  background-image: linear-gradient(90deg, #74EBD5 0%, #9FACE6 100%);
}

.card-deck {
  height: fit-content;
  flex-wrap: wrap;
  align-items: stretch;
  display: flow;
  width: 70%;
  margin-left: 300px;

}

.card {
  display: inline-block;
  padding: 5px;
  margin: 0.5%;
}

.card-text {
  height: 50px;
}

.card-img-top {
  width: 100%;
}
.btn-primary {
  background-color: #198754;
  border-color: #198754;
}

.btn-primary:hover {
  background-color: #12613e;
}
.btn-outline-success {
  border-color: #0093E9;
  background-color: #0093E9;
  background-image: linear-gradient(160deg, #0093E9 0%, #80D0C7 100%);
  color: white;
  border-radius: 10px;
  margin-left: 50px;
}
.btn-outline-success:hover{
  background: green;
}
footer {
  margin-top:10%;
}
.form-control{
  background: #80D0C7;
  border-color: #80D0C7;
}
.mt-5 {
  width: 15%;
  height: 100%;
  position: fixed;
  display:block;
  margin-left: 50px;
  margin-right: 50px;
  color: black;
  background: #f4f4f4;
}
</style>