<template>
  <!-- Verifica se a rota não é a de login ou a do formulário compartilhável -->
  <NavBar v-if="!ehLogin && !ehFormularioCompartilhavel" />

  <!-- Transição aplicada ao RouterView -->
  <transition name="fade" mode="out-in">
    <RouterView />
  </transition>
</template>

<script>
// Importação do componente Navbar
import NavBar from '../src/components/NavBar.vue';

export default {
  name: "App",
  components: {
    NavBar
  },
  computed: {
    ehLogin() {
      const ocultar = this.$route.path === '/' || this.$route.path === '/registrar';
      return ocultar;
    },
    ehFormularioCompartilhavel() {
      // Verifica se a rota contém "formulario" e um token (exemplo: /formulario/:token)
      return this.$route.path.startsWith('/formulario/');
    }
  }
};
</script>

<style>
body {
  background-color: rgb(48, 52, 57);
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center center;
  margin: 0;
  padding: 0;
}

/* Estilo da transição fade */
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s ease;
}
.fade-enter,
.fade-leave-to {
  opacity: 0;
}
</style>
