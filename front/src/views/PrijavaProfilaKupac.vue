<script>
import axios from 'axios'
import router from "@/router";
export default {
  name: "KupacUserView",
  data() {
    return {
      proizvodi: [],
      korisnik:'',
      isMenuOpen:false,
      imeProizvoda:null,
      proizvod:null,
      prodavac:'',
      razlogPrijave:'',
      podnosenjePrijaveDto:null,
    };
  },
  mounted() {
    this.getProizvodi();
    this.logged();
    this.getKorisnik();
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
    openMenu(imeP,id){
      this.isMenuOpen=true;
      this.imeProizvoda=imeP;
      this.fetchProizvod(id);
    },
    fetchProizvod(id) {
      axios.get(`http://localhost:8081/proizvod/lista-proizvoda/${id}`, { withCredentials: true })
          .then((response) => {
            //console.log(response.data);
            this.proizvod = response.data;
            this.prodavac=response.data.prodavac;
            console.log(this.prodavac);
          })
          .catch((error) => {
            console.log(error);
            alert("Ne postoji proizvod sa tim ID-em");
          });
    },
    posaljiPrijavu(id){
      axios.post(`http://localhost:8081/prijavaprofila/podnosiprijavu/kupac?id=${id}`, {
        razlogPrijave: this.razlogPrijave,
      }, { withCredentials: true })
          .then((response) => {
            //console.log(response.data);
            console.log(response.data);
            alert(response.data);
            this.$router.push('/home');
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
  <div><h1 class="proizvod">Kupljeni proizvodi</h1></div>
  <div class="card-deck">
    <div v-for="proizvod1 in this.korisnik.kupljeni_proizvodi" :key="proizvod1.id" class="card" style="width: 18rem;">
      <img :src="proizvod1.slika" class="card-img-top" alt="slika">
      <div class="card-body">
        <h5 class="card-title">{{proizvod1.naziv}}</h5>
        <p class="card-text">{{proizvod1.opis}}</p>
        <p class="card-text"><strong>Cena {{proizvod1.cena}} RSD</strong></p>
        <router-link :to="{ name: 'PrijavaProfilaKupac', params: {imeP:proizvod1.naziv,ID: proizvod1.id }}" @click.prevent="openMenu(proizvod1.naziv,proizvod1.id)" class="btn btn-primary">Podnesi prijavu za ovaj proizvod</router-link>
      </div>
    </div>
  </div>
  <div v-if="isMenuOpen" class="menu">
    <form @submit.prevent="posaljiPrijavu(this.prodavac.id)">
      <label for="complaint">Podnosite prijavu za proizvod {{this.imeProizvoda}},koji prodaje {{this.prodavac.ime}}</label>
      <input type="text" id="complaint" class="zalba" name="razlogPrijave" v-model="this.razlogPrijave" >
      <br>
      <button type="submit" class="posalji-prijavu" >Podnesi</button>
    </form>
  </div>
</template>

<style scoped>
.card-deck {
  height: fit-content;
  flex-wrap: wrap;
  align-items: stretch;
  display: flow;
  width: 70%;
  margin-left: 300px;

}
.zalba{
  width: 50%;
  border-radius: 50px;
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
</style>