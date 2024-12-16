<template>
  <!-- Componente Navbar reutilizável -->
  <NavBar />

 <RouterView />
  
</template>

<script>
// Importação do componente Navbar
import NavBar from '../src/components/NavBar.vue';

export default {
  name: "App",
  components: {
    NavBar
  }
};
</script>

<style>
body {
  background-color: rgb(32, 37, 43);
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center center;
  margin: 0;
  padding: 0;
}
</style>
