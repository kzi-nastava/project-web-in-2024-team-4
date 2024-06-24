<script>
import axios from "axios";
import router from "@/router";
import prijavaProfilaKupac from "@/views/PrijavaProfilaKupac.vue";

export default {
  name: "AdministratorView",
  computed: {
    prijavaProfilaKupac() {
      return prijavaProfilaKupac
    }
  },
  data() {
    return {
      korisnik: {},
      recenzije: [],
      review: {},
      izmenjeniKomentar:'',
      editRecenzijaId: null,
    };
  },
  mounted() {
    this.getLoggedUser();
    this.getRecenzije();
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
    /*
    logout() {
      localStorage.removeItem('korisnik');
      this.$router.push('/');
    },*/
    getRecenzije() {
      axios.get('http://localhost:8081/korisnik/administrator/recenzije/pregled', {withCredentials: true})
          .then((response) => {
            this.recenzije = response.data;
            console.log(response.data);
          })
          .catch((error) => {
            console.log(error);
          });
    },
    /*
    deleteRecenzija(Id) {
      axios.delete(`http://localhost:8081/obrisi-recenziju/${this.recenzije[Id].id}`, {withCredentials: true})
          .then((response) => {
            alert(response.data);
          })
          .catch((error) => {
            console.log(error);
          });
    },
    */
    goEdit(id) {
      axios.put(`http://localhost:8081/korisnik/administrator/recenzije/izmena/${id}?komentar=`+this.izmenjeniKomentar,this.korisnik, {withCredentials: true})
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
      this.editRecenzijaId = null;
      this.izmenjeniKomentar = '';
    },
    editRecenzija(id){
      this.editRecenzijaId=id;
    },
    deleteRecenzija(id){
      axios.post(`http://localhost:8081/korisnik/administrator/recenzije/obrisi?id=${id}`,this.korisnik, {withCredentials: true})
          .then((response) => {
            console.log(response.data);
          })
          .catch((error) => {
            console.log(error.data);
          });
    },
    goPrijaveProfila(){
      this.$router.push('/korisnik/logged/admin/prijaveprofila');
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
            <a class="nav-link active" aria-current="page" href="#" v-on:click="goPrijaveProfila()" >Prijave Profila</a>
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

  <section @load="getRecenzije" v-for="recenzija in recenzije" :key="recenzija.id" class="p-4 p-md-5 text-center text-lg-start shadow-1-strong rounded">
    <div class="row d-flex justify-content-center">
      <div id="reviews" class="col-md-10">
        <div class="card">
          <div class="card-body m-3">
            <div class="row">
              <div class="col-lg-4 d-flex justify-content-center align-items-center mb-4 mb-lg-0">
                <img :src="recenzija.korisnikDao.profilnaSlika" alt="user icon" id="recenzijaImg" class="rounded-circle img-fluid shadow-1" width="200" height="200" />
              </div>
              <div class="col-lg-8">
                <p class="fw-bold lead mb-2"><strong>{{recenzija.korisnikDao.ime}} {{recenzija.korisnikDao.prezime}} ({{recenzija.korisnikDao.uloga}})</strong></p>

                <!-- Prikaz forme za uređivanje ili originalnog komentara -->
                <div v-if="editRecenzijaId===recenzija.id">
                  <input
                      type="text" name="komentar"
                      v-model="this.izmenjeniKomentar"
                      class="form-control mb-2"
                  />
                  <button v-on:click="goEdit(recenzija.id)" class="btn btn-primary me-md-2">Save</button>
                  <button v-on:click="cancelEdit" class="btn btn-secondary">Cancel</button>
                </div>
                <div v-else>
                  <p class="text-muted fw-light mb-4">{{recenzija.komentar}}</p>
                </div>

                <p><b>Ocena:</b> {{recenzija.ocena}}</p>
                <p><b>Datum:</b> {{recenzija.datumRecenzije}}</p>
              </div>
              <div id="buttons" class="col-lg-4 d-flex justify-content-end align-items-end mb-4 mb-lg-0">
                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                  <form>
                    <button v-on:click="editRecenzija(recenzija.id)" id="editBtn" class="btn btn-primary me-md-2" type="button">Edit</button>
                    <button v-on:click="deleteRecenzija(recenzija.id)" id="deleteBtn" type="submit" class="btn btn-primary me-md-2">Delete Review</button>
                  </form>
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