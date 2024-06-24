<script>
import axios from "axios";
import router from "@/router";

export default {
  name: "EditProizvodForma",
  data() {
    return {
      trazeniKorisnik:null,
      proizvod:{
        ime:'',
        opis:'',
        slika:'',
        kategorija:{
          naziv:'',
        },
        cena:'',
        tipProdaje:'',
      },
    };
  },
  created() {
    this.getKorisnik();
    this.islogged();
    this.getinfoKorisnik();
    this.getProizvod();
  },
  methods: {
    getinfoKorisnik(){
      const id=this.$route.params.id;
      console.log(id);
      axios.get(`http://localhost:8081/korisnik/prodavac/pregled/${id}`,{withCredentials:true}).then((response) => {
        this.trazeniKorisnik = response.data;
       // console.log(this.trazeniKorisnik);
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
     // console.log(this.korisnik);
      },
    getProizvod(){
      const id=this.$route.params.id;
      axios.get(`http://localhost:8081/proizvod/lista-proizvoda/${id}`,{withCredentials:true}).then((response) => {
        this.proizvod = response.data;
       // console.log(this.proizvod);
      })
          .catch((error) => {
            alert(error.response.data);
          });
    },
    posaljiIzmene(){
      const id=this.$route.params.id;
      axios.put(`http://localhost:8081/korisnik/prodavac/azuriranjeProizvoda/${id}`,this.proizvod,{withCredentials:true}).then((response) => {
        alert(response.data);
        //console.log(response.data);
        this.$router.push('/logged/prodavac/pregled');
      })
          .catch((error) => {
            alert(error.response.data);
            this.$router.push('/logged/prodavac/pregled');
          });
    },
    }
};
</script>

<template>
  <div class="form-container">
      <h2>Dodavanje Proizvoda</h2>
      <div class="form-group">
        <label for="productName">Naziv Proizvoda</label>
        <input type="text" id="productName" name="ime" v-model="this.proizvod.ime" required>
      </div>
      <div class="form-group">
        <label for="description">Opis</label>
        <textarea id="description" name="opis" rows="4" v-model="this.proizvod.opis"  required></textarea>
      </div>
      <div class="form-group">
        <label for="image">Slika</label>
        <input type="text" id="image" name="slika"  v-model="this.proizvod.slika" required>
      </div>
      <div class="form-group">
        <label for="price">Cena</label>
        <input type="number" id="price" name="cena"  v-model="this.proizvod.cena" min="0" required>
      </div>
      <div class="form-group">
        <label for="category">Kategorija</label>
        <input type="text" id="category" name="kategorija" v-model="this.proizvod.kategorija.naziv" required>
      </div>
      <div class="form-group">
        <label for="saleType">Tip Prodaje</label>
        <select id="saleType" name="tipProdaje" v-model="this.proizvod.tipProdaje" required>
          <option value="Aukcija">Aukcija</option>
          <option value="FiksnaCena">Fiksna Cena</option>
        </select>
      </div>
      <button type="submit" v-on:click="posaljiIzmene()" >Posalji izmene</button>
  </div>
</template>

<style scoped>
body {
  font-family: Arial, sans-serif;
  background-color: #f4f4f4;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100vh;
  margin: 0;
}

.form-container {
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  width: 50%;
  position: relative;
  margin-left: 25%;
}

form h2 {
  margin-bottom: 20px;
  font-size: 24px;
  color: #333;
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

.form-group input,
.form-group textarea,
.form-group select {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.form-group input[type="file"] {
  padding: 3px;
}

button {
  width: 100%;
  padding: 10px;
  background-color: #28a745;
  color: #fff;
  border: none;
  border-radius: 4px;
  font-size: 16px;
  cursor: pointer;
}

button:hover {
  background-color: #218838;
}
</style>