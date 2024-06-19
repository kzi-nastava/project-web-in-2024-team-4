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
    };
  },
  mounted() {
    this.getProizvodi();
    this.searchProizvodi();
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

  <div><h1 class="proizvod">Proizvodi</h1></div>
  <div class="card-deck">
    <div v-for="proizvod in proizvodi" :key="proizvod.id" class="card" style="width: 18rem;">
      <img :src="proizvod.slika" class="card-img-top" alt="slika">
      <div class="card-body">
        <h5 class="card-title">{{proizvod.ime}}</h5>
        <p class="card-text">{{proizvod.opis}}</p>
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
  display: flex;
  height: fit-content;
  flex-wrap: wrap;
  align-items: stretch;
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
</style>