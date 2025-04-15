<template>
  <div class="flex items-center justify-center min-h-screen bg-gray-100">
    <div class="w-full max-w-md p-8 bg-white rounded-lg shadow-lg">
      <h1 class="text-2xl font-semibold text-center text-gray-700 mb-6">Login</h1>
      <form @submit.prevent="login">
        <div class="mb-4">
          <label for="login" class="block text-sm font-medium text-gray-600">Login</label>
          <input
            v-model="loginData.login"
            type="text"
            id="login"
            placeholder="Digite seu login"
            required
            class="w-full px-4 py-2 mt-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
          />
        </div>
        <div class="mb-6">
          <label for="senha" class="block text-sm font-medium text-gray-600">Senha</label>
          <input
            v-model="loginData.senha"
            type="password"
            id="senha"
            placeholder="Digite sua senha"
            required
            class="w-full px-4 py-2 mt-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
          />
        </div>
        <button
          type="submit"
          class="w-full py-2 text-white bg-indigo-600 rounded-lg hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500"
        >
          Entrar
        </button>
      </form>

      <!-- Link para a página de registro -->
      <p class="mt-4 text-sm text-center text-gray-600">
        Ainda não tem uma conta? 
        <router-link to="/registrar" class="text-indigo-600 hover:text-indigo-700">
          Cadastre-se aqui
        </router-link>
      </p>

      <p v-if="errorMessage" class="mt-4 text-sm text-center text-red-500">{{ errorMessage }}</p>
    </div>
  </div>
</template>

<script>
import AutenticacaoService from '@/services/AutenticacaoService';

export default {
  data() {
    return {
      loginData: {
        login: '',
        senha: ''
      },
      errorMessage: ''
    };
  },
  methods: {
    async login() {
      try {
        const response = await AutenticacaoService.login(this.loginData);
        const token = response.data.token;
        const login = this.loginData.login; // pega o valor digitado no campo

        // Armazena no localStorage
        localStorage.setItem('token', token);
        localStorage.setItem('login', login);

        this.$router.push('/despachados');
      } catch (error) {
        console.error('Erro ao fazer login:', error);
        this.errorMessage = 'Login ou senha inválidos!';
      }
    }

  }
};
</script>

<style scoped>

</style>
