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
            <a class="nav-link active" aria-current="page" href="/home">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Link</a>
          </li>
        </ul>
        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
          <button v-on:click="logout()" class="btn btn-outline-success">Logout</button>
          <div v-if="korisnik!=null" class="user-name-profil-img">
          <img src="https://cdn-icons-png.flaticon.com/256/6522/6522516.png" id="profile-photo">
          <p class="user-name"  ><strong>{{korisnik.korisnickoIme}}</strong></p>
          </div>
        </div>
      </div>
    </div>
  </nav>
  <div class="rating-form">
    <h2 class="oceni-prodavca">Oceni prodavca</h2>

     <input type="text" class="input-zvezdica" name="ocena" placeholder="unesite ocenu prodavca" required v-model="this.OcenjivanjeProdavcaDto.ocena">
      <span class="rating-counter"></span>

    <textarea class="komentar-recenzija" v-model="this.OcenjivanjeProdavcaDto.komentar" placeholder="Ovde unesite komentar sa recenzijom"></textarea>
    <button class="ostavi-recenziju" v-on:click="oceniprodavca()" >Pošalji recenziju</button>
  </div>
  <div v-if="proizvod">
    <h1 class="nazivProizvoda">{{ proizvod.ime }}</h1>
    <img :src="proizvod.slika" alt="slika proizvoda" class="product-image">
    <p><strong>{{ proizvod.opis }}</strong></p>
    <ul>
      <li><router-link :to="{ name: 'PrikazInformacijaKupca', params: { id: proizvod.prodavac.id,proizvodiNaProdaju:proizvod.prodavac.proizvodiNaProdaju, } }" class="btn btn-primary">Klikni da vidis vise informacija o prodavcu</router-link></li>
      <li><strong>Cena:</strong> {{ proizvod.cena }} RSD</li>
      <li><strong>Kategorija:</strong> {{ proizvod.kategorija.naziv }}</li>
    </ul>
    <div v-if="proizvod.tipProdaje=='FiksnaCena'">
      <button v-on:click="kupiProizvodFiksnaCena()"> Kupi proizvod</button>
    </div>
    <div v-else>
      <label>Najvisa cena do sada(Bidovana)</label>
      <input readonly v-model="this.najvisaCena">
      <br/>
      <label>Unesite cenu</label>
      <input placeholder="Ovde unosite cenu za aukciju" v-model="this.novaCena">
      <br/>
      <br/>
      <button v-on:click="kupiProizvodAukcija()">Prosledi Cenu za Aukciju</button>
    </div>

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
      proizvod: null,
      novaCena:null,
      message:'',
      najvisaCena:null,
      OcenjivanjeProdavcaDto:{
        komentar:'',
        ocena:'',
      },
    };
  },
  created() {
    this.fetchProizvod();
    this.getKorisnik();
    this.islogged();
  },
  methods: {
    fetchProizvod() {
      const id = this.$route.params.id;
      axios.get(`http://localhost:8081/proizvod/lista-proizvoda/${id}`, { withCredentials: true })
          .then((response) => {
            console.log(response.data);
            for(let ponuda of response.data.ponuda){
              if(ponuda.cena>this.najvisaCena)
                this.najvisaCena=ponuda.cena;
            }
            this.proizvod = response.data;
          })
          .catch((error) => {
            console.log(error);
            alert("Ne postoji proizvod sa tim ID-em");
          });
    },
    logout() {
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
    islogged(){
      if (localStorage.getItem('korisnik') == null) {
        this.$router.push('/home');
      }
    },
    getKorisnik(){
        this.korisnik = JSON.parse(localStorage.getItem('korisnik'));
    },kupiProizvodFiksnaCena(){
      const id = this.$route.params.id;
      console.log(this.korisnik);
      axios.post(`http://localhost:8081/proizvod/kupiproizvode?id=${id}`,this.korisnik,{ withCredentials: true })
          .then((response) => {
            console.log(response.data);
            this.message = response.data;
            alert(this.message);
          })
          .catch((error) => {
            if(error.response){
              alert(error.response.data);
            }
          });
    },kupiProizvodAukcija(){
    const id = this.$route.params.id;
    axios.post(`http://localhost:8081/proizvod/kupiproizvode?id=${id}&novaCena=${this.novaCena}`,this.korisnik,{ withCredentials: true })
        .then((response) => {
         console.log(response.data);
          this.message = response.data;
          alert("Uspesno dodata ponuda, ponuda="+this.novaCena);
        })
        .catch((error) => {
          if(error.response){
            alert(error.response.data);
          }
        });
  },
    oceniprodavca(){
      const id=this.proizvod.prodavac.id;
      console.log(id);
      axios.post(`http://localhost:8081/korisnik/kupac/oceniProdavca/${id}`,this.OcenjivanjeProdavcaDto,{ withCredentials: true })
          .then((response) => {
            console.log(response.data);
            this.message = response.data;
          })
          .catch((error) => {
            alert(error.response.data);
          });
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

.btn-outline-success {
  border-color: #0093E9;
  background-color: #0093E9;
  background-image: linear-gradient(160deg, #0093E9 0%, #80D0C7 100%);
  color: white;
  border-radius: 10px;
  width: 100px;
  height: 50px;
  position: relative;
  margin-top: 15px;
}
.btn-outline-success:hover{
  background: green;
}

footer {
  margin-top:10%;
}
#profile-photo{
  width: 80%;
}

.user-name-profil-img user-name{
  width: 60px;
  padding-top: 25px;
  margin-right: 20px;
}
.user-name-profil-img{
  width: 20%;
}
.navbar {
  background-color: #74EBD5;
  background-image: linear-gradient(90deg, #74EBD5 0%, #9FACE6 100%);
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

footer {
  margin-top:10%;
}

.rating-form{
  margin: 0;
  width: 50vmin;
  align-items: center;
  justify-content: center;
  box-sizing: border-box;
  margin-left: 73%;
  position: absolute;
  margin-top: 5%;

}
.oceni-prodavca{

  position: relative;
  font-family: "Rage Italic";
}
.komentar-recenzija{
  border-radius: 50px;
  height: 150px;
  border-color: black;
}
.rating-form {
  background: #fff;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  text-align: center;
  width: 300px;
}

.rating-form .oceni-prodavca {
  margin-bottom: 20px;
  color: #333;
}

.rating-stars {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
  position: relative;
  font-size: 0;
}

.rating-stars .input-zvezdica {
  display: none;
}

.star {
  width: 5vmin;
  height: 5vmin;
  background: #000b;
  display: inline-flex;
  cursor: pointer;
  margin: 0.5vmin 0.65vmin;
  transition: all 2s ease 0s;
  clip-path: polygon(50% 0%, 66% 32%, 100% 38%, 78% 64%, 83% 100%, 50% 83%, 17% 100%, 22% 64%, 0 38%, 34% 32%);
}

.star0 {
  display: none;
}

.star:before {
  width: 90%;
  height: 90%;
  content: "";
  background: orange;
  z-index: -1;
  display: block;
  margin-left: 5%;
  margin-top: 5%;
  clip-path: polygon(50% 0%, 66% 32%, 100% 38%, 78% 64%, 83% 100%, 50% 83%, 17% 100%, 22% 64%, 0 38%, 34% 32%);
  background: linear-gradient(90deg, yellow, orange 30% 50%, #184580 50%, 70%, #173a75 100%);
  background-size: 205% 100%;
  background-position: 0 0;
}

.star:hover:before {
  transition: all 1s ease 0s;
}

.input-zvezdica:checked + .star ~ .star:before {
  background-position: 100% 0;
  transition: all 1s ease 0s;
}

.input-zvezdica:checked + .star:hover:before {
  background-position: 0%;
}

#rs1:checked ~ .rating-counter:before {
  content: "1";
}

#rs2:checked ~ .rating-counter:before {
  content: "2";
}

#rs3:checked ~ .rating-counter:before {
  content: "3";
}

#rs4:checked ~ .rating-counter:before {
  content: "4";
}

#rs5:checked ~ .rating-counter:before {
  content: "5";
}

.star:hover ~ .rating-counter:before {
  color: #9aacc6 !important;
  transition: all 0.5s ease 0s;
  animation: pulse 1s ease 0s infinite;
}

@keyframes pulse {
  50% { font-size: 6.25vmin; }
}

.star1:hover ~ .rating-counter:before {
  content: "1" !important;
}

.star2:hover ~ .rating-counter:before {
  content: "2" !important;
}

.star3:hover ~ .rating-counter:before {
  content: "3" !important;
}

.star4:hover ~ .rating-counter:before {
  content: "4" !important;
}

.star5:hover ~ .rating-counter:before {
  content: "5" !important;
}

.input-zvezdica:checked:hover ~ .rating-counter:before {
  animation: none !important;
  color: #ffab00 !important;
}

.komentar-recenzija {
  width: 100%;
  height: 100px;
  border: 1px solid #ddd;
  border-radius: 5px;
  padding: 10px;
  margin-bottom: 20px;
  font-size: 16px;
  resize: none;
  box-sizing: border-box;
}

.komentar-recenzija::placeholder {
  color: #bbb;
}

.ostavi-recenziju {
  background-color: #28a745;
  color: #fff;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
}

.ostavi-recenziju:hover {
  background-color: #218838;
}

</style>
