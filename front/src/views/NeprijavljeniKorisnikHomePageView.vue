<script>
import axios from 'axios'

export default {
  name: "GuestUserView",
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
    };
  },
  mounted() {
    this.getProizvodi();
    this.searchProizvodi();
    this.getKategorije();
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
    searchProizvodi(){
      axios.get(`http://localhost:8081/proizvod/search?opis=${this.searchTerm}&naziv=${this.searchTerm}`,{withCredentials:true}).then((response) => {
        this.proizvodi = response.data;
      })
          .catch((error) => {
            console.log(error);
            alert("Ne posotji proizvod sa tim nazivom");
          });
    },
    seeMore(id) {
      axios.get(`http://localhost:8081/proizvod/lista-proizvoda/${id}`, { withCredentials: true })
          .then((response) => {
            const index = this.proizvodi.findIndex(proizvod => proizvod.id === id);
            if (index !== -1) {
              this.$set(this.proizvodi, index, response.data);
            }
          })
          .catch((error) => {
            console.log(error);
            alert("Ne postoji proizvod sa tim ID-em");
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
     }
    }
};
</script>

<template>
  <nav  class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
      <a class="navbar-brand" href="/">WebShop</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="#">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Link</a>
          </li>
        </ul>
        <form class="d-flex" role="search" @submit.prevent="searchProizvodi">
          <input class="form-control me-4" type="search" placeholder="Search" v-model="searchTerm"  aria-label="Search">
          <button class="btn btn-outline-success" v-on:click="searchProizvodi()" type="submit">Search</button>
        </form>
        <div class="d-grid gap-1 d-md-flex justify-content-md-end">
          <button id="loginBtn" class="btn btn-primary me-md-2" style="margin-left: 40px" type="button">Login</button>
          <button id="registerBtn" class="btn btn-primary" type="button">Register</button>
        </div>
      </div>
    </div>
  </nav>

  <div class="container mt-5" @submit.prevent="filter">
    <form>
      <div class="mb-4">
        <label for="minPrice" class="form-label">Najmanja cena:</label>
        <input type="number" class="form-control" id="minPrice" v-model="minCena" name="minPrice" placeholder="Najmanja Cena">
      </div>

      <div class="mb-4">
        <label for="maxPrice" class="form-label">Najveca cena:</label>
        <input type="number" class="form-control" id="maxPrice" v-model="maxCena" name="maxPrice" placeholder="Najveca Cena">
      </div>

      <div class="mb-4">
        <label class="form-label">Kategorije:</label>

        <div class="form-check">
          <div v-for="kategorija in kategorije">
          <input class="form-check-input" type="radio" v-model="kategorijaPick" name="category" id="{{kategorija.id}}" :value="kategorija.naziv">
            <label class="form-check-label" for="category{{kategorija.id}}">
              {{kategorija.naziv}}
            </label>
          </div>
        </div>
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
        <router-link :to="{ name: 'ProizvodDetalji', params: { id: proizvod.id } }" class="btn btn-primary">Vidi još</router-link>
      </div>
    </div>
  </div>


  <footer>
    <p style="user-select: none">&copy; {{ new Date().getFullYear() }} - All rights reserved</p>
  </footer>
</template>

<style scoped>
.proizvod{
  padding-top: 15px;
}
.navbar {
  background-color: #74EBD5;
  background-image: linear-gradient(90deg, #74EBD5 0%, #9FACE6 100%);


}
#loginBtn {
  border-color: #0093E9;
  border-radius: 10px;
  background-color: #0093E9;
  background-image: linear-gradient(160deg, #0093E9 0%, #80D0C7 100%);

}

#loginBtn:hover {
  background: #198754;
}

#registerBtn {
  border-color: #0093E9;
  border-radius: 10px;
  background-color: #0093E9;
  background-image: linear-gradient(160deg, #0093E9 0%, #80D0C7 100%);

}

#registerBtn:hover {
  background: #198754;
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
}
.btn-outline-success:hover{
  background: green;
}
.d-flex{
  width: 100%;
  display: block;
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
  height: 70%;
  position: fixed;
  display:block;
  margin-left: 50px;
  margin-right: 50px;
  background: grey;
  color: #74EBD5;
}

</style>