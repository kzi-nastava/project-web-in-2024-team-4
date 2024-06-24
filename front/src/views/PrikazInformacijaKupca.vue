<script>
import axios from "axios";

export default{
  data() {
    return {
      korisnik: null,
      proizvodiNaProdaju:null,
    };
  },
    created() {
      this.fetchKorisnik();
    },
    methods: {
      fetchKorisnik() {
        const id = this.$route.params.id;
        axios.get(`http://localhost:8081/korisnik/kupac/pregled/${id}`, { withCredentials: true })
            .then((response) => {
              console.log(response.data);
              this.korisnik = response.data;
            })
            .catch((error) => {
              console.log(error);
              alert("Ne postoji proizvod sa tim ID-em");
            });
      }
}
};
</script>
<template>  <div class="container">
  <h2>Informacije o prodavcu</h2>
  <form id="user-form" v-if="korisnik">
    <label for="first-name">Ime:</label>
    <input type="text" id="first-name" v-model="korisnik.ime" name="ime" readonly>

    <label for="last-name">Prezime:</label>
    <input type="text" id="last-name" v-model="korisnik.prezime" name="prezime" readonly>

    <label for="username">Korisničko ime:</label>
    <input type="text" id="username"  v-model="korisnik.korisnickoIme" name="korisnickoIme" readonly>

    <label for="email">Email:</label>
    <input type="email" id="email" v-model="korisnik.emailAdresa"  name="emailAdresa" readonly>

    <label for="phone">Broj telefona:</label>
    <input type="tel" id="phone" v-model="korisnik.brojTelefona"  name="brojTelefona" readonly>

    <label for="dob">Datum rođenja:</label>
    <input type="text" id="dob" v-model="korisnik.datumRodjenja" name="datumRodjenja" readonly>

    <label for="phone-number">Broj telefona</label>
    <input type="text" id="phone-number" v-model="korisnik.brojTelefona" name="brojTelefona" readonly>

    <label for="avg-rating">Prosecna ocena</label>
    <input type="text" id="phone-number" v-model="korisnik.prosecnaOcena" name="brojTelefona" readonly>

    <label for="bio">O sebi:</label>
    <textarea id="bio" v-model="korisnik.opis" name="opis"></textarea>
  </form>
</div>
  <div><h1 class="proizvod">Proizvodi</h1></div>
  <div class="card-deck">
    <div v-if="korisnik" v-for="proizvod in korisnik.proizvodiNaProdaju" :key="proizvod.id" class="card" style="width: 18rem;">
      <img :src="proizvod.slika" class="card-img-top" alt="slika">
      <div class="card-body">
        <h5 class="card-title">{{proizvod.ime}}</h5>
        <p class="card-text">{{proizvod.opis}}</p>
        <p class="card-text"><strong>Cena {{proizvod.cena}} RSD</strong></p>
        <router-link :to="{ name: 'ProizvodDetaljiKupacView', params: { id: proizvod.id } }" class="btn btn-primary">Vidi još</router-link>
      </div>
    </div>
  </div>
  <div><h1 class="proizvod">Recenzije koje je dobio korisnik</h1></div>
  <div class="card-deck">
    <div v-if="korisnik" v-for="recenzija in korisnik.dobijeneRecenzije" :key="recenzija.id" class="card" style="width: 18rem;">
      <div class="card-body">
        <h5 class="card-title">Komentar: {{recenzija.komentar}}</h5>
        <p class="card-text">Ocena: {{recenzija.ocena}}</p>
        <p class="card-text"><strong>Datum Recenzije {{recenzija.datumRecenzije}}</strong></p>
      </div>
    </div>
  </div>
</template>
<style>
body {
  font-family: Arial, sans-serif;
  background-color: #f4f4f4;
  margin: 0;
  padding: 0;
}

.container {
  max-width: 600px;
  margin: 50px auto;
  padding: 20px;
  background-color: #fff;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h2, h3 {
  color: #333;
}

label {
  display: block;
  margin-top: 10px;
  font-weight: bold;
}

input[type="text"],
input[type="email"],
input[type="tel"],
input[type="password"],
input[type="date"],
input[type="file"],
textarea,
select {
  width: 100%;
  padding: 10px;
  margin-top: 5px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  text-align: center;
}

textarea {
  height: 100px;
}

button {
  background-color: #28a745;
  color: white;
  padding: 10px 15px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #218838;
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
</style>