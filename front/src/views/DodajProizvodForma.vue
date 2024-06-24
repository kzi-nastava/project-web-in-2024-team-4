<script>
import axios from "axios";
import router from "@/router";

export default {
  name: "DodajProizvodForma",
  data() {
    return {
      proizvodi: [],
      korisnik:'',
      postavljanjeProdajeDto:{
        naziv:'',
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
  mounted() {
    this.logged();
  },
  methods: {
    dodajProizvod(){
          axios.post(`http://localhost:8081/korisnik/prodavac/postaviProdaju`,this.postavljanjeProdajeDto, {withCredentials: true})
              .then((response) => {
                this.proizvodi = response.data;
                console.log(this.proizvodi);
                this.$router.push('/');
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
    },
  }
}
</script>

<template>
  <div class="form-container">
    <form @submit.prevent="dodajProizvod()">
      <h2>Dodavanje Proizvoda</h2>
      <div class="form-group">
        <label for="productName">Naziv Proizvoda</label>
        <input type="text" id="productName" name="naziv" v-model="this.postavljanjeProdajeDto.naziv" required>
      </div>
      <div class="form-group">
        <label for="description">Opis</label>
        <textarea id="description" name="opis" rows="4" v-model="this.postavljanjeProdajeDto.opis"  required></textarea>
      </div>
      <div class="form-group">
        <label for="image">Slika</label>
        <input type="text" id="image" name="slika"  v-model="this.postavljanjeProdajeDto.slika" required>
      </div>
      <div class="form-group">
        <label for="price">Cena</label>
        <input type="number" id="price" name="cena"  v-model="this.postavljanjeProdajeDto.cena" min="0" required>
      </div>
      <div class="form-group">
        <label for="category">Kategorija</label>
        <input type="text" id="category" name="kategorija" v-model="this.postavljanjeProdajeDto.kategorija.naziv" required>
      </div>
      <div class="form-group">
        <label for="saleType">Tip Prodaje</label>
        <select id="saleType" name="tipProdaje" v-model="this.postavljanjeProdajeDto.tipProdaje" required>
          <option value="Aukcija">Aukcija</option>
          <option value="FiksnaCena">Fiksna Cena</option>
        </select>
      </div>
      <button type="submit" >Dodaj Proizvod</button>
    </form>
  </div>
</template>

<style scoped>
/* styles.css */
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