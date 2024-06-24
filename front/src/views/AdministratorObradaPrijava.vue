<script>
import axios from "axios";
import router from "@/router";
export default {
  name: "AdministratorObradaPrijava.vue",
  data() {
    return {
      korisnik: {},
      prijave: [],
      review: {},
      razlogOdbijanjaPrijave:'',
      editRecenzijaId: null,
      showDenialReason:null,
    };
  },
  mounted() {
    this.getLoggedUser();
    this.getPrijave();
  },
  methods: {
    getLoggedUser() {
      this.korisnik = JSON.parse(localStorage.getItem('korisnik'));
      console.log(this.korisnik);
    },logout() {
      axios.post('http://localhost:8081/korisnik/logout', {}, { withCredentials: true })
          .then((response) => {
            this.message = response.data;
            localStorage.removeItem('korisnik');
            router.push('/home');
          })
          .catch((error) => {
            if (error.response && error.response.status === 403) {
              this.message = 'Zabranjeno: Niste prijavljeni';
            } else {
              this.message = 'Došlo je do greške pri odjavi';
            }
          });
    },
    getPrijave() {
      axios.get('http://localhost:8081/korisnik/administrator/podneseneprijave/pregled', {withCredentials: true})
          .then((response) => {
            this.prijave = response.data;
            console.log(response.data);

          })
          .catch((error) => {
            console.log(error.response.data);
          });
    },
    prihvatiPrijavu(id){
      axios.post(`http://localhost:8081/korisnik/administrator/podneseneprijave/obrada/${id}?odluka=true`,this.korisnik, {withCredentials: true})
          .then((response) => {
            this.prijave = response.data;
            console.log(response.data);
            window.location.reload();
          })
          .catch((error) => {
            console.log(error.response.data);
          });
    },odbijPrijavu(id) {
      axios.post(`http://localhost:8081/korisnik/administrator/podneseneprijave/obrada/${id}?odluka=false&razlogOdbijanja=`+this.razlogOdbijanjaPrijave,this.korisnik, {withCredentials: true})
          .then((response) => {
            //this.recenzije = response.data;
            console.log(response.data);
            alert(response.data);
            this.cancelEdit();
            window.location.reload();
          })
          .catch((error) => {
            console.log(error);
            alert(error.response.data);
          });
    },cancelEdit() {
      this.showDenialReason = null;
      this.razlogOdbijanjaPrijave = '';
    },
    obdijenEdit(id){
      this.showDenialReason=id;
    }

  }
};
</script>

<template>
  <nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
      <a class="navbar-brand" href="/">WebShop</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="/">Recenzije</a>
          </li>
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="#" >Prijave Profila</a>
          </li>
        </ul>
        <div @load="getLoggedUser" class="d-grid gap-2 d-md-flex justify-content-md-end">
          <div v-if="korisnik != null" id="user" class="d-grid gap-2 d-md-flex justify-content-md-end">
            <img id="icon" :src="korisnik.profilnaSlika" alt="user icon">
            <p><b>{{korisnik.korisnickoIme}}</b></p>
          </div>
          <a v-on:click="logout()" href="#">Logout</a>
        </div>
      </div>
    </div>
  </nav>

  <section @load="getPrijave" v-for="prijava in prijave" :key="prijava.id" class="p-4 p-md-5 text-center text-lg-start shadow-1-strong rounded">
    <div class="row d-flex justify-content-center">
      <div id="reviews" class="col-md-10">
        <div class="card">
          <div class="card-body m-3">
            <div class="row">
              <div class="col-lg-4 d-flex justify-content-center align-items-center mb-4 mb-lg-0">
                <img :src="prijava.korisnikPodnositelj.profilnaSlika" alt="user icon" id="recenzijaImg" class="rounded-circle img-fluid shadow-1" width="200" height="200" />
              </div>
              <div class="col-lg-8">
                <p class="fw-bold lead mb-2">Podneo prijavu <strong>{{ prijava.korisnikPodnositelj.korisnickoIme }}</strong></p>
                <p class="fw-bold lead mb-2">Upucena prijava <strong>{{ prijava.korisnikOdnosiSe.korisnickoIme }}</strong></p>
                <p class="fw-bold lead mb-2">Razlog prijave: {{ prijava.razlog_prijave }}</p>
                <p class="fw-bold lead mb-2">Status prijave: {{ prijava.statusPrijave }}</p>
                <p class="fw-bold lead mb-2">Datum podnosenja prijave: {{ prijava.datum_podnosenja_prijave }}</p>
              </div>
              <div id="buttons" class="col-lg-4 d-flex justify-content-end align-items-end mb-4 mb-lg-0">
                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                 <form @submit.prevent="obdijenEdit(prijava.id)">
<!--                   Prvi button accept prijavu-->
                   <button id="accept-button" class="btn btn-primary me-md-2" type="button" v-on:click="prihvatiPrijavu(prijava.id)" ><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-square" viewBox="0 0 16 16">
                     <path d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2z"/>
                     <path d="M10.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425z"/>
                   </svg></button>
<!--                   Drugi button odbi prijavu-->
                 <button v-on:click="obdijenEdit(prijava.id)"  id="denide-button" type="submit" class="btn btn-primary me-md-2"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x" viewBox="0 0 16 16">
                   <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708"/>
                 </svg></button>
                </form>
                </div>
              </div>
              <div v-if="this.showDenialReason===prijava.id" class="col-lg-12 mt-4">
                <div class="card">
                  <div class="card-body">
                    <h5 class="card-title">Razlog odbijanja recenzije</h5>
                    <form @submit.prevent="odbijPrijavu(prijava.id)">
                      <div class="mb-3">
                        <label for="denialReason" class="form-label">Razlog</label>
                        <textarea id="denialReason" class="form-control" v-model="this.razlogOdbijanjaPrijave"></textarea>
                      </div>
                      <button type="submit" class="btn btn-primary me-md-2" v-on:click="odbijPrijavu(prijava.id)" >Pošalji</button>
                      <button type="button" class="btn btn-secondary" v-on:click="cancelEdit()">Cancel</button>
                    </form>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<style scoped>
#user {
  cursor: pointer;
}

#icon {
  width: 40px;
  height: 40px;
}

.col-lg-8 {
  width: fit-content;
}

#deleteBtn {
  background-color: #e80000;
  border-color: #e80000;
}
</style>