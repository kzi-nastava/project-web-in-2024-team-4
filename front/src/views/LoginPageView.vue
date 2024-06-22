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
          <button id="registerBtn" class="btn btn-primary" v-on:click="register()" type="button">Register</button>
        </div>
      </div>
    </div>
  </nav>
  <section class="container">
    <div class="login-container">
      <div class="circle circle-one"></div>
      <div class="form-container">
        <img src="https://raw.githubusercontent.com/hicodersofficial/glassmorphism-login-form/master/assets/illustration.png" alt="illustration" class="illustration" />
        <h1 class="opacity">LOGIN</h1>
        <form @submit.prevent="login">
          <input type="text"  v-model="korisnik.korisnickoIme" placeholder="USERNAME" />
          <input type="password" v-model="korisnik.lozinka" placeholder="PASSWORD" />
          <button class="opacity" v-on:click="login()">SUBMIT</button>
          <p v-if="errorMessage" class="error">{{errorMessage}}</p>
        </form>
      </div>
      <div class="circle circle-two"></div>
    </div>
    <div class="theme-btn-container"></div>
  </section>
</template>

<script>
import axios from "axios";
import router from "@/router";
import  {inject,ref} from "vue";

export default {
  name: "LoginView",
  data: function () {
    return {
      korisnik: {},
      errorMessage:'',
      searchTerm:'',

    };
  },
  methods: {
    login: function () {
      axios
          .post("http://localhost:8081/korisnik/prijava-korisnika", this.korisnik, {
            withCredentials: true,
          })
          .then((res) => {
            console.log(res);
            localStorage.setItem("korisnik", JSON.stringify(res.data));
            // Provera uloge korisnika
            if (res.data.uloga === 'KUPAC') {
              console.log(res.data.uloga);
              this.$router.push("/korisnik/logged/kupac");
            } else if (res.data.uloga === 'PRODAVAC') {
              console.log("Korisnik je prodavac");
              this.$router.push("/korisnik/logged/prodavac");
            }else {
              this.$router.push("/korisnik/logged/admin");
            }
          })
          .catch((err) => {
            if (err.response) {
              this.errorMessage = err.response.data; // Čuvanje poruke o grešci iz odgovora
              this.message = ''; // Čišćenje poruke o uspehu
            } else if (err.request) {
              this.errorMessage = "Nema odgovora od servera"; // Kada nema odgovora od servera
              this.message = ''; // Čišćenje poruke o uspehu
            } else {
              this.errorMessage = "Greška u slanju zahteva"; // Generalna greška u slanju zahteva
              this.message = ''; // Čišćenje poruke o uspehu
            }
          });
    },
    home(){
      router.push('/home');
    },
    register(){
      router.push('/korisnik/registracija')
    }
  }
};
</script>

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
  font-size: 2.5rem;
}

.container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.login-container {
  position: relative;
  width: 22.2rem;
}
.form-container {
  border: 1px solid hsla(0, 0%, 65%, 0.158);
  box-shadow: 0 0 36px 1px rgba(0, 0, 0, 0.2);
  border-radius: 10px;
  backdrop-filter: blur(20px);
  z-index: 99;
  padding: 2rem;
  -webkit-border-radius: 10px;
  -moz-border-radius: 10px;
  -ms-border-radius: 10px;
  -o-border-radius: 10px;
}

.login-container form input {
  display: block;
  padding: 14.5px;
  width: 100%;
  margin: 2rem 0;
  color: var(--color);
  outline: none;
  background-color: #9191911f;
  border: none;
  border-radius: 5px;
  font-weight: 500;
  letter-spacing: 0.8px;
  font-size: 15px;
  backdrop-filter: blur(15px);
  -webkit-border-radius: 5px;
  -moz-border-radius: 5px;
  -ms-border-radius: 5px;
  -o-border-radius: 5px;
}

.login-container form input:focus {
  box-shadow: 0 0 16px 1px rgba(0, 0, 0, 0.2);
  animation: wobble 0.3s ease-in;
  -webkit-animation: wobble 0.3s ease-in;
}

.login-container form button {
  background-color: var(--primary-color);
  color: var(--color);
  display: block;
  padding: 13px;
  border-radius: 5px;
  outline: none;
  font-size: 18px;
  letter-spacing: 1.5px;
  font-weight: bold;
  width: 100%;
  cursor: pointer;
  margin-bottom: 2rem;
  transition: all 0.1s ease-in-out;
  border: none;
  -webkit-border-radius: 5px;
  -moz-border-radius: 5px;
  -ms-border-radius: 5px;
  -o-border-radius: 5px;
  -webkit-transition: all 0.1s ease-in-out;
  -moz-transition: all 0.1s ease-in-out;
  -ms-transition: all 0.1s ease-in-out;
  -o-transition: all 0.1s ease-in-out;
}

.login-container form button:hover {
  box-shadow: 0 0 10px 1px rgba(0, 0, 0, 0.15);
  transform: scale(1.02);
  -webkit-transform: scale(1.02);
  -moz-transform: scale(1.02);
  -ms-transform: scale(1.02);
  -o-transform: scale(1.02);
}

.circle {
  width: 8rem;
  height: 8rem;
  background: var(--primary-color);
  border-radius: 50%;
  -webkit-border-radius: 50%;
  -moz-border-radius: 50%;
  -ms-border-radius: 50%;
  -o-border-radius: 50%;
  position: absolute;
}

.illustration {
  position: absolute;
  top: -14%;
  right: -2px;
  width: 90%;
}

.circle-one {
  top: 0;
  left: 0;
  z-index: -1;
  transform: translate(-45%, -45%);
  -webkit-transform: translate(-45%, -45%);
  -moz-transform: translate(-45%, -45%);
  -ms-transform: translate(-45%, -45%);
  -o-transform: translate(-45%, -45%);
}

.circle-two {
  bottom: 0;
  right: 0;
  z-index: -1;
  transform: translate(45%, 45%);
  -webkit-transform: translate(45%, 45%);
  -moz-transform: translate(45%, 45%);
  -ms-transform: translate(45%, 45%);
  -o-transform: translate(45%, 45%);
}
.opacity {
  opacity: 0.6;
}

.theme-btn-container {
  position: absolute;
  left: 0;
  bottom: 2rem;
}

@keyframes wobble {
  0% {
    transform: scale(1.025);
    -webkit-transform: scale(1.025);
    -moz-transform: scale(1.025);
    -ms-transform: scale(1.025);
    -o-transform: scale(1.025);
  }
  25% {
    transform: scale(1);
    -webkit-transform: scale(1);
    -moz-transform: scale(1);
    -ms-transform: scale(1);
    -o-transform: scale(1);
  }
  75% {
    transform: scale(1.025);
    -webkit-transform: scale(1.025);
    -moz-transform: scale(1.025);
    -ms-transform: scale(1.025);
    -o-transform: scale(1.025);
  }
  100% {
    transform: scale(1);
    -webkit-transform: scale(1);
    -moz-transform: scale(1);
    -ms-transform: scale(1);
    -o-transform: scale(1);
  }
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