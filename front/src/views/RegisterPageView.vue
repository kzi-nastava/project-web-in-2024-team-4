<script>
import axios from "axios";
import router from "@/router";
export default {
  name: "RegisterPageView",
  data: function () {
    return {
      korisnik: {},
    };
  },
  mounted() {
    this.isUserLogged();
  },
  methods:{
    register(){
      axios.post("http://localhost:8081/korisnik/registracija",this.korisnik,{withCredentials:true,})
          .then((res) => {
            console.log(res.data);
            alert("" + res.data);

            this.$router.push("/home");
          })
          .catch((err) => {
            alert("Svi podaci moraju biti popunjeni");
          });
    },login(){
      router.push("/korisnik/prijava-korisnika");
    },
    home(){
      router.push('/home');
    }, isUserLogged() {
      if (localStorage.getItem('korisnik') == null) {
        this.$router.push('/korisnik/registracija');
      }
      else {
        const korisnik = JSON.parse(localStorage.getItem('korisnik'));
        if (korisnik.uloga === 'KUPAC') {
          this.$router.push("/korisnik/logged/kupac");
        } else if (korisnik.ulogauloga === 'PRODAVAC') {
          this.$router.push("/korisnik/logged/prodavac");
        }else {
          this.$router.push("/korisnik/logged/admin");
        }
      }
    },
  }
  };
</script>
<template>
  <nav  class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
      <a class="navbar-brand" href="/home">WebShop</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" v-on:click="home()" href="#">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Link</a>
          </li>
        </ul>
        <div class="d-grid gap-1 d-md-flex justify-content-md-end">
          <button id="loginBtn" class="btn btn-primary me-md-2" v-on:click="login()" style="margin-left: 40px" type="button">Login</button>
        </div>
      </div>
    </div>
  </nav>
  <h1>Register</h1>
  <form class="row g-3" @submit.prevent="register">
    <div class="col-md-6">
      <label for="inputIme" class="form-label">Ime</label>
      <input type="text" class="form-control" id="inputIme" v-model="korisnik.ime">
    </div>
    <div class="col-md-6">
      <label for="inputPrezime" class="form-label">Prezime</label>
      <input type="text" class="form-control" id="inputPrezime" v-model="korisnik.prezime">
    </div>
    <div class="col-md-6">
      <label for="inputEmail4" class="form-label">Email</label>
      <input type="email" class="form-control" id="inputEmail4" v-model="korisnik.emailAdresa">
    </div>
    <div class="col-md-6">
      <label for="inputUsername" class="form-label">Korisnicko ime</label>
      <input type="text" class="form-control" id="inputUsername" v-model="korisnik.korisnickoIme">
    </div>
    <div class="col-12">
      <label for="inputPhone" class="form-label">Telefon</label>
      <input type="text" class="form-control" id="inputPhone" placeholder="+381 64..." v-model="korisnik.brojTelefona">
    </div>
    <div class="col-md-6">
      <label for="inputPassword" class="form-label">Lozinka</label>
      <input type="password" class="form-control" id="inputPassword" v-model="korisnik.lozinka">
    </div>
    <div class="col-md-6">
      <label for="inputRole" class="form-label">Uloga</label>
      <select id="inputRole" class="form-select" v-model="korisnik.uloga">
        <option selected>Izaberi...</option>
        <option>KUPAC</option>
        <option>PRODAVAC</option>
      </select>
    </div>
    <div class="col-md-6">
      <button class="btn btn-primary" id="btn-register">Registruj se</button>
    </div>
  </form>
</template>

<style scoped>
:root {
  --background: #1a1a2e;
  --color: #ffffff;
  --primary-color: #0f3460;
}

* {
  box-sizing: border-box;
}

html {
  scroll-behavior: smooth;
}

body {
  margin: 0;
  box-sizing: border-box;
  font-family: "poppins";
  background: var(--background);
  color: var(--color);
  letter-spacing: 1px;
  transition: background 0.2s ease;
  -webkit-transition: background 0.2s ease;
  -moz-transition: background 0.2s ease;
  -ms-transition: background 0.2s ease;
  -o-transition: background 0.2s ease;
}

a {
  text-decoration: none;
  color: var(--color);
}

h1 {
  font-size: 3.5rem;
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
.btn-primary {
  background-color: #74EBD5;
  background-image: linear-gradient(90deg, #74EBD5 0%, #9FACE6 100%);
}

.btn-primary:hover {
  background-color: #12613e;
}
footer {
  margin-top:10%;
}
.form-control{
  background: white;
  border-color: red;
}
#inputRole{
  background: white;
  border-color: red;
}
</style>