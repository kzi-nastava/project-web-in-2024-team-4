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
            <a class="nav-link active" v-on:click="home()" aria-current="page" href="#">Home</a>
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
          <button id="loginBtn" class="btn btn-primary me-md-2" v-on:click="login()" style="margin-left: 40px" type="button">Login</button>
          <button id="registerBtn" class="btn btn-primary" type="button">Register</button>
        </div>
      </div>
    </div>
  </nav>
  <div v-if="proizvod">
    <h1 class="nazivProizvoda">{{ proizvod.ime }}</h1>
    <img :src="proizvod.slika" alt="slika proizvoda" class="product-image">
    <p><strong>{{ proizvod.opis }}</strong></p>
    <ul>
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
      proizvod: null
    };
  },
  created() {
    this.fetchProizvod();
  },
  methods: {
    fetchProizvod() {
      const id = this.$route.params.id;
      axios.get(`http://localhost:8081/proizvod/lista-proizvoda/${id}`, { withCredentials: true })
          .then((response) => {
            this.proizvod = response.data;
          })
          .catch((error) => {
            console.log(error);
            alert("Ne postoji proizvod sa tim ID-em");
          });
    },
    home() {
      router.push('/home');
    }
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
