<script>
import axios from 'axios';

export default {
  name: "ProfileUpdate",
  data() {
    return {
      userData: {
        ime: '',
        prezime: '',
        korisnickoIme: '',
        emailAdresa: '',
        brojTelefona: '',
        lozinka: '',
        datumRodenja: '',
        profilnaSlika: '',
        opis: ''
      }
    };
  },
  mounted() {
    this.updateUser();
  },
  methods: {
    updateUser() {
      axios.put('http://localhost:8081/logged/prodavacUpdate', this.userData, {withCredentials: true})
      .then((res) => {
        console.log(res);
        if(res.data.uloga === 'KUPAC') {
          this.$router.push('/kupac_view');
        }
        if(res.data.uloga === 'PRODAVAC') {
          this.$router.push('/prodavac_view');
        }
      })
      .catch((err) => {
        console.log(err);
        alert(err.response.data);
      })
  }
  }
};

</script>

<template>
  <div class="main">
    <h2>Azuriraj profil</h2>

    <form class="profile-form"  @submit.prevent="updateUser">
      <label for="ime">Ime:</label>
      <input v-model="userData.ime" type="text" id="ime" name="ime" placeholder="Unesite ime">

      <label for="prezime">Prezime:</label>
      <input v-model="userData.prezime" type="text" id="prezime" name="prezime" placeholder="Unesite prezime">

      <label for="korisnickoIme">Korisničko ime:</label>
      <input v-model="userData.korisnickoIme" type="text" id="korisnickoIme" name="korisnickoIme" placeholder="Unesite korisnicko ime">

      <label for="emailAdresa">Email:</label>
      <input v-model="userData.emailAdresa" type="emailAdresa" id="emailAdresa" name="emailAdresa" placeholder="Unesite email adresu">

      <label for="mail">Broj Telefona:</label>
      <input v-model="userData.brojTelefona" type="brojTelefona" id="brojTelefona" name="brojTelefona" placeholder="Unesite broj telefona">

      <label for="lozinka">Password:</label>
      <input v-model="userData.lozinka" type="text" id="lozinka" name="lozinka" placeholder="Unesite password">

      <label for="datumRodjenja">Datum rodjenja:</label>
      <input v-model="userData.datumRodjenja" type="date" id="datumRodjenja" name="datumRodjenja">

      <label for="profilnaSlika">Profilna slika:</label>
      <input v-model="userData.profilnaSlika" type="text" id="profilnaSlika" name="profilnaSlika" placeholder="Dodajte novu sliku">

      <label for="opis">Opis:</label>
      <textarea v-model="userData.opis" type="text" id="opis" name="opis" placeholder="Unesite opis"></textarea>

      <button type="submit">Ažuriraj profil</button>
    </form>
  </div>
</template>

<style scoped>

.main {
  max-width: 500px;
  margin: 0 auto;
  padding: 20px;
}

.profile-form {
  display: grid;
  gap: 10px;
}

</style>