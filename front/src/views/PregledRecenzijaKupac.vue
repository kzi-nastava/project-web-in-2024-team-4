<script>
import axios from "axios";
import router from "@/router";

export default {
  name: "KupacUserView",
  data() {
    return {
      proizvodi: [],
      message:'',
      korisnik:'',
      recenzije:[],
    };
  },
  mounted() {
    this.getProizvodi();
    this.logged();
    this.getKorisnik();
    this.pregledRecenzija();
  },
  methods: {
    getProizvodi() {
      axios.get('http://localhost:8081/proizvod/lista-proizvoda', {withCredentials: true})
          .then((response) => {
            this.proizvodi = response.data;
          })
          .catch((error) => {
            console.log(error);
            alert(error.message);
          });
    },
    logged(){
      if (localStorage.getItem('korisnik') == null) {
        this.$router.push('/home');
      }
    },
    getKorisnik(){
      this.korisnik=JSON.parse(localStorage.getItem('korisnik'));
      console.log(this.korisnik);
      },
    pregledRecenzija(){
      axios.get('http://localhost:8081/korisnik/kupac/pregledRecenzija', {withCredentials: true})
          .then((response) => {
            this.recenzije = response.data;
          })
          .catch((error) => {
            console.log(error);
            alert(error.response.data);
          });
    },
  }
};
</script>

<template>
<h1 class="uvod">Sve recenzije koje je kupac {{this.korisnik.korisnickoIme}} dao prodavcima i primuo od prodavaca</h1>
<!--  <p>{{this.recenzije}}</p>-->
  <div class="main-container">
    <div class="cards" v-for="rec in this.recenzije">
      <div class="card card1">
        <div>
          <ul>
<!--          <li><i class="fa-solid fa-star"></i>Korisnik koji je dao <b>{{rec.korisnikDao}}</b></li>-->
          </ul>
        </div>
        <p class="close"><i class="fa-solid fa-xmark"></i></p>
        <p class="desc">{{rec.komentar}}</p>
        <p class="">Recenzija ostavljena dana {{rec.datumRecenzije}}</p>
        <p class="read-more">
          <a class="readMore" href="#">Procitaj Vise (Trenutno nefunkcionalno) <i class="fas fa-arrow-right"></i></a>
        </p>
      </div>
    </div>
  </div>
</template>

<style scoped>
.uvod{
  width: 100%;
  display: flex;
  position: relative;
}
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: sans-serif;
  display: flex;
  flex-wrap: wrap;
}

.main-container {
  padding: 30px;
}
.card {
  margin: 15px;
  padding: 15px;
  width: 400px;
  display: grid;
  min-height: 200px;
  border-radius: 10px;
  transition: all 0.2s;
  grid-template-rows: 20px 50px 1fr 50px;
  box-shadow: 0px 6px 10px rgba(74, 70, 74, 0.414);
}

.card:hover {
  transform: scale(1.01);
  box-shadow: 0px 6px 10px rgba(0, 0, 0, 0.4);
}

.readMore,
.close,
.card div {
  cursor: pointer;
  position: relative;
  text-decoration: none;
  color: rgba(255, 255, 255, 0.9);
}

.card div ul {
  display: flex;
  list-style: none;
}

.card div ul li {
  margin: 0 0.3rem;
  font-size: 1.1rem;
}

.desc {
  color: rgb(66, 59, 59);
}

.readMore::after {
  content: "";
  position: absolute;
  top: 25px;
  left: 0;
  width: 0%;
  height: 3px;
  transition: all 0.5s;
  background-color: rgba(255, 255, 255, 0.6);
}

.readMore:hover {
  color: #fff;
}

.readMore:hover::after {
  width: 100%;
}
.card div {
  grid-row: 2/3;
  font-size: 30px;
}

.read-more {
  grid-row: 4/5;
  align-self: center;
}

.card1 {
  background: #3fbafe;
}


@media (max-width: 1600px) {
  .cards {
    justify-content: center;
  }
}
</style>