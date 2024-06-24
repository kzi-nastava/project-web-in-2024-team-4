<template>
  <div class="container">
    <h2>Izmena korisničkih informacija</h2>
    <form id="user-form" @submit.capture="updateKorisnik">
      <label for="first-name">Ime:</label>
      <input type="text" id="first-name" v-model="korisnik.ime" name="ime" required>

      <label for="last-name">Prezime:</label>
      <input type="text" id="last-name" v-model="korisnik.prezime" name="prezime" required>

      <label for="username">Korisničko ime:</label>
      <input type="text" id="username" v-model="korisnik.korisnickoIme" name="korisnickoIme" required>

      <label for="email">Email:</label>
      <input type="email" id="email" v-model="korisnik.emailAdresa" name="emailAdresa" required>

      <label for="phone">Broj telefona:</label>
      <input type="tel" id="phone" v-model="korisnik.brojTelefona" name="brojTelefona" required>

      <label for="password"> Nova Lozinka:</label>
      <input type="password" id="password" v-model="korisnik.novaLozinka" name="novaLozinka" required>

      <label for="dob">Datum rođenja:</label>
      <input type="date" id="dob" v-model="korisnik.datumRodjenja" name="datumRodjenja" required>

      <label for="profile-pic">Profilna slika:</label>
      <input type="text" id="profile-pic" v-model="korisnik.profilnaSlika" name="profilnaSlika" accept="image/*">

      <label for="bio">O sebi:</label>
      <textarea id="bio" v-model="korisnik.opis" name="opis"></textarea>

      <h3>Za promenu korisničkog imena, mejla ili lozinke, unesite trenutnu lozinku</h3>

      <label for="current-password">Trenutna lozinka:</label>
      <input type="password" id="current-password" placeholder="stara lozinka" v-model="korisnik.lozinka" name="lozinka">

      <button type="submit" v-on:click="updateKorisnik()">Sačuvaj promene</button>
    </form>
  </div>
</template>
<style>
/* styles.css */
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

</style>
<script>
import axios from 'axios'
import router from "@/router";
export default {
  name: "ProdavacUpdateInfoView",
  data() {
    return {
      message:'',
      korisnik:'',
    };
  },
  mounted() {
    this.logged();
    this.getKorisnik();
  },
  methods: {
    logged(){
      if (localStorage.getItem('korisnik') == null) {
        this.$router.push('/home');
      }
    },
    getKorisnik(){
      this.korisnik=JSON.parse(localStorage.getItem('korisnik'));
      this.korisnik.lozinka='';
      this.korisnik.novaLozinka='';
    },
    updateKorisnik(){
      axios.put(`http://localhost:8081/korisnik/logged/prodavacUpdate`,this.korisnik,{withCredentials:true}).then((response) => {
        localStorage.setItem('korisnik',JSON.stringify(this.korisnik));
        this.$router.push('/korisnik/logged/prodavac');
      })
          .catch((error) => {
            console.log(error);
            alert(error.response.data);
          });
    },
  }
};
</script>