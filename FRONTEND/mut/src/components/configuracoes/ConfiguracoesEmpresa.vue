<template>
  <div class="max-w-xl mx-auto p-8 bg-gray-900 text-white rounded-lg shadow-xl mt-8">
    <h1 class="text-3xl font-semibold mb-6 text-center">Configurar Campos do Formulário</h1>

    <div v-for="(value, field) in camposConfiguraveis" :key="field" class="mb-5 flex items-center">
      <input
        type="checkbox"
        :id="field"
        v-model="camposConfiguraveis[field]"
        class="w-5 h-5 text-sky-600 focus:ring-2 focus:ring-sky-600 transition duration-200 ease-in-out mr-4"
      />
      <label :for="field" class="text-lg font-medium capitalize">{{ field }}</label>
    </div>

    <button
      @click="salvarConfiguracoes"
      class="w-full mt-6 bg-sky-600 hover:bg-sky-700 text-white font-semibold px-6 py-3 rounded-lg shadow-lg transform hover:scale-105 transition duration-300 ease-in-out"
    >
      Salvar Configurações
    </button>
  </div>
</template>

<script>
import ConfiguracaoService from '@/services/ConfiguracaoService';

export default {
  name: "ConfiguracoesEmpresa",
  data() {
    return {
      camposConfiguraveis: {
        nome: true,
        cpf: true,
        email: true,
        telefone: true,
        cargo: true,
        salario: true,
        dataAdmissao: true,
        ativo: true,
      },
    };
  },
  mounted() {
    this.carregarConfiguracoes();
  },
  methods: {
    async carregarConfiguracoes() {
      try {
        const response = await ConfiguracaoService.listarTodos();
        if (response.data) {
          this.camposConfiguraveis = response.data;
        }
      } catch (error) {
        console.error("Erro ao carregar configurações:", error);
      }
    },
    async salvarConfiguracoes() {
      try {
        await ConfiguracaoService.salvar(this.camposConfiguraveis);
        alert("Configurações salvas com sucesso!");
      } catch (error) {
        console.error("Erro ao salvar configurações:", error);
      }
    },
  },
};
</script>

<style scoped>
/* Estilos personalizados para o componente */
.bg-gray-900 {
  background-color: #1f2937;
}

.text-sky-600 {
  color: #0ea5e9;
}

.text-sky-700 {
  color: #0284c7;
}

button {
  transition: all 0.3s ease-in-out;
}

input[type="checkbox"] {
  transition: all 0.2s ease-in-out;
}

button:hover {
  transform: translateY(-2px);
}

button:active {
  transform: translateY(2px);
}

input[type="checkbox"]:checked {
  background-color: #0ea5e9;
  border-color: #0ea5e9;
}

input[type="checkbox"]:focus {
  outline: none;
  box-shadow: 0 0 0 2px rgba(14, 165, 233, 0.3);
}
</style>
