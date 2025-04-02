<template>
  <div class="max-w-4xl mx-auto p-6 bg-gray-800 text-white rounded-lg shadow mt-8">
    <h1 class="text-2xl font-bold mb-4">Registros de Funcionários Despachados</h1>

    <table class="min-w-full table-auto">
      <thead>
        <tr>
          <th class="px-4 py-2 text-left">Nome</th>
          <th class="px-4 py-2 text-left">Cargo</th>
          <th class="px-4 py-2 text-left">Telefone</th>
          <th class="px-4 py-2 text-left">Data de Admissão</th>
          <th class="px-4 py-2 text-left">Ação</th> <!-- Nova coluna para o botão -->
        </tr>
      </thead>
      <tbody>
        <tr v-for="funcionario in funcionarios" :key="funcionario.id">
          <td class="px-4 py-2">{{ funcionario.nome }}</td>
          <td class="px-4 py-2">{{ funcionario.cargo }}</td>
          <td class="px-4 py-2">{{ funcionario.telefone }}</td>
          <td class="px-4 py-2">{{ funcionario.dataAdmissao }}</td>
          <td class="px-4 py-2">
            <button 
              @click="registrarFuncionario(funcionario)" 
              class="bg-sky-600 hover:bg-sky-700 text-white font-semibold px-4 py-2 rounded"
            >
              Registrar
            </button>
          </td> <!-- Botão "Registrar" para cada funcionário -->
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import FuncionarioService from '@/services/FuncionarioService';

export default {
  name: 'RegistrosProntos',
  data() {
    return {
      funcionarios: [],
    };
  },
  mounted() {
    this.carregarFuncionariosDespachados();
  },
  methods: {
    async carregarFuncionariosDespachados() {
      try {
        const response = await FuncionarioService.listarFuncionariosDespachados();
        this.funcionarios = response.data;
      } catch (error) {
        console.error("Erro ao carregar funcionários:", error);
      }
    },
    registrarFuncionario(funcionario) {
      // Implementação para registrar o funcionário
      console.log('Registrar o funcionário:', funcionario);
      // Aqui você pode adicionar a lógica de registrar o funcionário ou abrir um modal
    }
  },
};
</script>

<style scoped>
/* Estilos personalizados para o componente */
table {
  width: 100%;
  border-collapse: collapse;
}

th, td {
  border: 1px solid #ddd;
}

th {
  background-color: #333;
  color: white;
}

td {
  background-color: #444;
}
</style>
