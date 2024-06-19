<template>
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
</style>
