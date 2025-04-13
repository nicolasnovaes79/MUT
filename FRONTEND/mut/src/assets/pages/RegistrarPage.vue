<template>
  <div class="flex items-center justify-center min-h-screen bg-gray-100">
    <div class="w-full max-w-md p-8 bg-white rounded-lg shadow-lg">
      <h1 class="text-3xl font-semibold text-center text-gray-700 mb-6">Registrar Empresa</h1>
      <form @submit.prevent="registrar">
        <!-- Login -->
        <div class="mb-4">
          <label for="login" class="block text-sm font-medium text-gray-600">Login</label>
          <input
            v-model="registerData.login"
            type="text"
            id="login"
            placeholder="Digite o login"
            required
            class="w-full px-4 py-2 mt-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
          />
        </div>

        <!-- Senha -->
        <div class="mb-4">
          <label for="senha" class="block text-sm font-medium text-gray-600">Senha</label>
          <input
            v-model="registerData.senha"
            type="password"
            id="senha"
            placeholder="Digite a senha"
            required
            class="w-full px-4 py-2 mt-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
          />
        </div>

        <!-- Nome da Empresa -->
        <div class="mb-4">
          <label for="nome" class="block text-sm font-medium text-gray-600">Nome da Empresa</label>
          <input
            v-model="registerData.nome"
            type="text"
            id="nome"
            placeholder="Digite o nome da empresa"
            required
            class="w-full px-4 py-2 mt-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
          />
        </div>

        <!-- CNPJ -->
        <div class="mb-4">
          <label for="cnpj" class="block text-sm font-medium text-gray-600">CNPJ</label>
          <input
            v-model="registerData.cnpj"
            type="text"
            id="cnpj"
            placeholder="Digite o CNPJ"
            required
            class="w-full px-4 py-2 mt-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
          />
        </div>

        <!-- Endereço -->
        <div class="mb-4">
          <label for="endereco" class="block text-sm font-medium text-gray-600">Endereço</label>
          <input
            v-model="registerData.endereco"
            type="text"
            id="endereco"
            placeholder="Digite o endereço"
            required
            class="w-full px-4 py-2 mt-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
          />
        </div>

        <!-- Telefone -->
        <div class="mb-4">
          <label for="telefone" class="block text-sm font-medium text-gray-600">Telefone</label>
          <input
            v-model="registerData.telefone"
            type="text"
            id="telefone"
            placeholder="Digite o telefone"
            required
            class="w-full px-4 py-2 mt-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
          />
        </div>

        <!-- Email -->
        <div class="mb-4">
          <label for="email" class="block text-sm font-medium text-gray-600">Email</label>
          <input
            v-model="registerData.email"
            type="email"
            id="email"
            placeholder="Digite o email"
            required
            class="w-full px-4 py-2 mt-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
          />
        </div>

        <!-- Role -->
        <div class="mb-4">
          <label for="role" class="block text-sm font-medium text-gray-600">Role</label>
          <select
            v-model="registerData.role"
            id="role"
            class="w-full px-4 py-2 mt-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
          >
            <option value="CLIENTE">Cliente</option>
            <option value="USER">Usuário</option>
          </select>
        </div>

        <button
          type="submit"
          class="w-full py-2 text-white bg-indigo-600 rounded-lg hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500"
        >
          Cadastrar
        </button>
      </form>
      <p v-if="errorMessage" class="mt-4 text-sm text-center text-red-500">{{ errorMessage }}</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      registerData: {
        login: '',
        senha: '',
        nome: '',
        cnpj: '',
        endereco: '',
        telefone: '',
        email: '',
        role: 'CLIENTE'
      },
      errorMessage: ''
    };
  },
  methods: {
    async registrar() {
  console.log(this.registerData.nome);  // Adicione esta linha para verificar o valor de nome
  try {
    await axios.post('http://localhost:8080/api/auth/registrar', this.registerData);
    this.$router.push('/'); // Depois de cadastrar, manda pra tela de login
  } catch (error) {
    this.errorMessage = 'Erro ao registrar!';
    console.error(error);
  }
}

  }
};
</script>

<style scoped>
.register-container {
  max-width: 400px;
  margin: auto;
  padding: 20px;
}
</style>
