<script>
import axios from "axios";
import router from "@/router";

export default {
  name: "DetaljanPrikazProfilaIzProdavca.vue",
  data() {
    return {
      trazeniKorisnik:null,
      ulogaKorisnika:this.$route.params.uloga,
    };
  },
  created() {
    this.getKorisnik();
    this.islogged();
    this.getinfoKorisnik();
    this.setUloga();
    },
  methods: {
    getinfoKorisnik(){
      const id=this.$route.params.id;
      console.log(id);
      axios.get(`http://localhost:8081/korisnik/prodavac/pregled/${id}`,{withCredentials:true}).then((response) => {
        this.trazeniKorisnik = response.data;
        console.log(this.trazeniKorisnik);
      })
          .catch((error) => {
            alert(error.response.data);
          });
    },
    islogged(){
      if (localStorage.getItem('korisnik') == null) {
        this.$router.push('/home');
      }
    },
    getKorisnik(){
      this.korisnik = JSON.parse(localStorage.getItem('korisnik'));
      console.log(this.korisnik);
    },
    setUloga(){
      //this.ulogaKorisnika=this.$route.params.uloga;
      console.log(this.ulogaKorisnika);
    }
  }
};
</script>

<template>
  <div class="container">
    <h2>Informacije o prodavcu</h2>
    <form id="user-form" v-if="trazeniKorisnik">
      <label for="first-name">Ime:</label>
      <input type="text" id="first-name" v-model="trazeniKorisnik.ime" name="ime" readonly>

      <label for="last-name">Prezime:</label>
      <input type="text" id="last-name" v-model="trazeniKorisnik.prezime" name="prezime" readonly>

      <label for="username">Korisničko ime:</label>
      <input type="text" id="username"  v-model="trazeniKorisnik.korisnickoIme" name="korisnickoIme" readonly>

      <label for="email">Email:</label>
      <input type="email" id="email" v-model="trazeniKorisnik.emailAdresa"  name="emailAdresa" readonly>

      <label for="phone">Broj telefona:</label>
      <input type="tel" id="phone" v-model="trazeniKorisnik.brojTelefona"  name="brojTelefona" readonly>

      <label for="dob">Datum rođenja:</label>
      <input type="text" id="dob" v-model="trazeniKorisnik.datumRodjenja" name="datumRodjenja" readonly>

      <label for="phone-number">Broj telefona</label>
      <input type="text" id="phone-number" v-model="trazeniKorisnik.brojTelefona" name="brojTelefona" readonly>

      <label for="avg-rating">Prosecna ocena</label>
      <input type="text" id="phone-number" v-model="trazeniKorisnik.prosecnaOcena" name="brojTelefona" readonly>

      <label for="bio">O sebi:</label>
      <textarea id="bio" v-model="trazeniKorisnik.opis" name="opis"></textarea>
    </form>
  </div>
  <div><h1 class="proizvod">Proizvodi</h1></div>
  <div class="card-deck" v-if="this.ulogaKorisnika==='KUPAC'">
    <div v-if="trazeniKorisnik" v-for="proizvod in trazeniKorisnik.kupljeniProizvodi" :key="proizvod.id" class="card" style="width: 18rem;">
      <img :src="proizvod.slika" class="card-img-top" alt="slika">
      <div class="card-body">
        <h5 class="card-title">{{proizvod.ime}}</h5>
        <p class="card-text">{{proizvod.opis}}</p>
        <p class="card-text"><strong>Cena {{proizvod.cena}} RSD</strong></p>
      </div>
    </div>
  </div>
  <div v-else class="card-deck">
    <div v-if="trazeniKorisnik" v-for="proizvod in trazeniKorisnik.proizvodiNaProdaju" :key="proizvod.id" class="card" style="width: 18rem;">
      <img :src="proizvod.slika" class="card-img-top" alt="slika">
      <div class="card-body">
        <h5 class="card-title">{{proizvod.ime}}</h5>
        <p class="card-text">{{proizvod.opis}}</p>
        <p class="card-text"><strong>Cena {{proizvod.cena}} RSD</strong></p>
      </div>
    </div>
  </div>
  <div><h1 class="proizvod">Recenzije koje je dobio korisnik</h1></div>
  <div class="card-deck">
    <div v-if="trazeniKorisnik" v-for="recenzija in trazeniKorisnik.dobijeneRecenzije" :key="recenzija.id" class="card" style="width: 18rem;">
      <div class="card-body">
        <h5 class="card-title">Komentar: {{recenzija.komentar}}</h5>
        <p class="card-text">Ocena: {{recenzija.ocena}}</p>
        <p class="card-text"><strong>Datum Recenzije {{recenzija.datumRecenzije}}</strong></p>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>