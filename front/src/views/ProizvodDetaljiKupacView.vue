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
            <a class="nav-link" href="#">Link</a>
          </li>
        </ul>
        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
          <button v-on:click="logout()" class="btn btn-outline-success">Logout</button>
          <div v-if="korisnik!=null" class="user-name-profil-img">
          <img src="https://cdn-icons-png.flaticon.com/256/6522/6522516.png" id="profile-photo">
          <p class="user-name"  ><strong>{{korisnik.korisnickoIme}}</strong></p>
          </div>
        </div>
      </div>
    </div>
  </nav>

  <div v-if="proizvod">
    <h1 class="nazivProizvoda">{{ proizvod.ime }}</h1>
    <img :src="proizvod.slika" alt="slika proizvoda" class="product-image">
    <p><strong>{{ proizvod.opis }}</strong></p>
    <ul>
      <li><router-link :to="{ name: 'PrikazInformacijaKupca', params: { id: proizvod.prodavac.id,proizvodiNaProdaju:proizvod.prodavac.proizvodiNaProdaju, } }" class="btn btn-primary">Klikni da vidis vise informacija o prodavcu</router-link></li>
      <li><strong>Cena:</strong> {{ proizvod.cena }} RSD</li>
      <li><strong>Kategorija:</strong> {{ proizvod.kategorija.naziv }}</li>
    </ul>
  </div>
  <div v-else>
    <p>Proizvod se učitava ili ne postoji.</p>
  </div>
</template>

<script>
import axios from 'axios';
import router from "@/router";

export default {
  data() {
    return {
      proizvod: null,
    };
  },
  created() {
    this.fetchProizvod();
    this.getKorisnik();
    this.islogged();
  },
  methods: {
    fetchProizvod() {
      const id = this.$route.params.id;
      axios.get(`http://localhost:8081/proizvod/lista-proizvoda/${id}`, { withCredentials: true })
          .then((response) => {
            console.log(response.data);
            this.proizvod = response.data;
          })
          .catch((error) => {
            console.log(error);
            alert("Ne postoji proizvod sa tim ID-em");
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
    islogged(){
      if (localStorage.getItem('korisnik') == null) {
        this.$router.push('/home');
      }
    },
    getKorisnik(){
        this.korisnik = JSON.parse(localStorage.getItem('korisnik'));
    },
  }
};
</script>

<style scoped>
ul li{
  list-style-type: none;
  padding: 0;
  margin: 0;
}
.product-image {
  max-width: 100%;
  height: auto;
}
.nazivProizvoda{
  padding: 15px;
}
.navbar {
  background-color: #74EBD5;
  background-image: linear-gradient(90deg, #74EBD5 0%, #9FACE6 100%);


}

.btn-outline-success {
  border-color: #0093E9;
  background-color: #0093E9;
  background-image: linear-gradient(160deg, #0093E9 0%, #80D0C7 100%);
  color: white;
  border-radius: 10px;
  width: 100px;
  height: 50px;
  position: relative;
  margin-top: 15px;
}
.btn-outline-success:hover{
  background: green;
}

footer {
  margin-top:10%;
}
#profile-photo{
  width: 80%;
}

.user-name-profil-img user-name{
  width: 60px;
  padding-top: 25px;
  margin-right: 20px;
}
.user-name-profil-img{
  width: 20%;
}
.navbar {
  background-color: #74EBD5;
  background-image: linear-gradient(90deg, #74EBD5 0%, #9FACE6 100%);
}

.btn-outline-success {
  border-color: #0093E9;
  background-color: #0093E9;
  background-image: linear-gradient(160deg, #0093E9 0%, #80D0C7 100%);
  color: white;
  border-radius: 10px;
}
.btn-outline-success:hover{
  background: green;
}

footer {
  margin-top:10%;
}

</style>
