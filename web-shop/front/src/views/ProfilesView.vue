<script>
import axios from 'axios';

export default {
  name: 'ProfileView',
  data() {
    return {
      korisnik: {}
    };
  },
    created() {
      this.prikaziProfil();
    },
  methods: {
    prikaziProfil() {
      const id = this.$route.params.id;
      console.log(id);
      axios.get(`http://localhost:8081/prodavac/pregled/${id}`, {withCredentials: true, headers: {
        'Cache-Control': 'no-cache'
      }})
          .then((response) => {
            this.korisnik = response.data;
          })
          .catch((error) => {
            console.log(error);
          });
    }
  },
};
</script>

<template>
    <div> {{korisnik.profilnaSlika}}</div>
    <div>  Korisnik : {{korisnik.ime}} {{korisnik.prezime}}</div>
    <div>  Uloga : {{korisnik.Uloga}}</div>
    <div>  Broj Telefona: {{korisnik.brojTelefona}}</div>
    <div>  Prosecna ocena: {{korisnik.prosecnaOcena}} </div>


    <div v-if="korisnik.Uloga === 'KUPAC'"> Kupljeni proizvodi:
      <div v-for="proizvod in korisnik.kupljeniProizvodi"> {{proizvod.naziv}} {{proizvod.cena}} </div>
      <div> Recenzije: </div>
        <div v-for="recenzija in korisnik.recenzije"> {{recenzija.ocena}} {{recenzija.komentar}} </div>
  </div>
  <div v-if="korisnik.Uloga === 'PRODAVAC'"> Proizvodi na prodaju:
    <div v-for="proizvod in korisnik.proizvodi"> {{proizvod.naziv}} {{proizvod.cena}} </div>
    <div> Recenzije: </div>
    <div v-for="recenzija in korisnik.recenzije"> {{recenzija.ocena}} {{recenzija.komentar}}</div>
  </div>



</template>

<style scoped>

</style>