<template>
  <div class="min-h-screen text-white flex justify-center">
    <div class="w-full max-w-5xl">
      <!-- Campo de pesquisa -->
      <div class="px-4">
        <input
          type="text"
          v-model="searchQuery"
          placeholder="Pesquisar por nome"
          class="px-4 py-2 mt-8 mb-4 border border-gray-600 shadow-sm focus:ring-blue-500 focus:border-blue-500 bg-gray-900 text-white placeholder-gray-400"
        />
      </div>

      <div class="flex">
        <!-- Lista de Cards com rolagem -->
        <div
          class="espaco-entre-cards overflow-y-auto pr-1 barra-customizada"
          style="max-height: 75vh; width: 30vh;"
        >
          <CardComponent
            v-for="funcionario in funcionariosFiltrados"
            :key="funcionario.id"
            :funcionario="funcionario"
            :selected="funcionarioEmEdicao && funcionarioEmEdicao.id === funcionario.id"
            @edit="iniciarEdicao(funcionario)"
          />
        </div>

        <!-- Formulário de edição com foto -->
        <div
          v-if="funcionarioEmEdicao"
          class="p-6 bg-gray-800 border border-gray-900 shadow-sm ml-2 overflow-y-auto"
          style="height: 75vh; width: 90vh;"
        >
          <!-- Conteúdo do formulário de edição -->
          <h3 class="text-2xl font-bold mb-4">Editar Funcionário</h3>
          <div class="flex flex-col md:flex-row">
            <!-- Coluna do formulário -->
            <div class="md:w-2/3 pr-4">
              <!-- Formulário de edição -->
              <form @submit.prevent="atualizarFuncionario">
                <!-- Campos do formulário -->
                <div class="mb-4">
                  <label for="nome" class="block text-sm">Nome</label>
                  <input
                    type="text"
                    id="nome"
                    v-model="funcionarioEmEdicao.nome"
                    class="mt-1 block w-full px-3 py-2 text-sm border border-gray-600 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 bg-white text-black placeholder-gray-400"
                    required
                  />
                </div>
                <div class="mb-4">
                  <label for="email" class="block text-sm">Email</label>
                  <input
                    type="email"
                    id="email"
                    v-model="funcionarioEmEdicao.email"
                    class="mt-1 block w-full px-3 py-2 text-sm border border-gray-600 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 bg-white text-black placeholder-gray-400"
                    required
                  />
                </div>
                <div class="mb-4">
                  <label for="cargo" class="block text-sm">Cargo</label>
                  <input
                    type="text"
                    id="cargo"
                    v-model="funcionarioEmEdicao.cargo"
                    class="mt-1 block w-full px-3 py-2 text-sm border border-gray-600 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 bg-white text-black placeholder-gray-400"
                    required
                  />
                </div>
                <div class="mb-4">
                  <label for="salario" class="block text-sm">Salário</label>
                  <input
                    type="number"
                    step="0.01"
                    id="salario"
                    v-model="funcionarioEmEdicao.salario"
                    class="mt-1 block w-full px-3 py-2 text-sm border border-gray-600 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 bg-white text-black placeholder-gray-400"
                    required
                  />
                </div>
                <div class="mb-4">
                  <label for="dataAdmissao" class="block text-sm">Data de Admissão</label>
                  <input
                    type="date"
                    id="dataAdmissao"
                    v-model="funcionarioEmEdicao.dataAdmissao"
                    class="mt-1 block w-full px-3 py-2 text-sm border border-gray-600 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 bg-white text-black placeholder-gray-400"
                    required
                  />
                </div>

                <!-- Botões de ação -->
                <div class="flex flex-wrap gap-2">
                  <button
                    type="submit"
                    class="inline-flex items-center px-3 py-2 text-white bg-sky-600 hover:bg-sky-700 focus:ring-2 focus:outline-none focus:ring-blue-300"
                    style="font-size: small;"
                  >
                    Salvar
                  </button>
                  <button
                    v-if="!funcionarioEmEdicao.despachado"
                    type="button"
                    @click="despacharFuncionario"
                    class="inline-flex items-center px-3 py-2 text-white bg-green-500 hover:bg-green-600 focus:ring-2 focus:outline-none focus:ring-green-300"
                    style="font-size: small;"
                  >
                    Despachar
                  </button>
                  <button
                    v-else
                    type="button"
                    @click="cancelarDespachoFuncionario"
                    class="inline-flex items-center px-3 py-2 text-white bg-red-500 hover:bg-red-600 focus:ring-2 focus:outline-none focus:ring-red-300"
                    style="font-size: small;"
                  >
                    Cancelar
                  </button>
                  <button
                    type="button"
                    @click="excluirFuncionario"
                    class="inline-flex items-center px-3 py-2 text-white bg-gray-500 hover:bg-gray-600 focus:ring-2 focus:outline-none focus:ring-gray-300"
                    style="font-size: small;"
                  >
                    Excluir
                  </button>
                </div>
              </form>
            </div>
            <!-- Coluna da foto -->
            <div class="md:w-1/3 flex items-start justify-center pt-2">
              <img
                :src="funcionarioEmEdicao.foto ? funcionarioEmEdicao.foto : 'https://cdn-icons-png.flaticon.com/512/147/147144.png'"
                alt="Foto do Funcionário"
                class="w-32 h-32 rounded-full object-cover"
              />
            </div>
          </div>
        </div>
      </div>

      <!-- Modal para exclusão -->
      <ModalDefault
        v-if="showModal"
        :isVisible="showModal"
        @update:isVisible="showModal = $event"
        @confirm="acaoModalConfirmada"
      />
    </div>
    <!-- Componente Popup de Mensagem -->
    <PopupMessage
      v-if="showPopup"
      :message="popupMessage"
      :type="popupType"
      :duration="3000"
      @close="showPopup = false"
    />
  </div>
</template>

<script>
import CardComponent from './componentes-filhos/CardFuncionario.vue';
import FuncionarioService from '../../services/FuncionarioService';
import ModalDefault from '../modais/ModalDefault.vue';
import PopupMessage from '../popup/Popup.vue';

export default {
  name: 'GestorRegistros',
  components: { CardComponent, ModalDefault, PopupMessage },
  data() {
    return {
      funcionarios: [],
      funcionarioEmEdicao: null,
      searchQuery: '',
      showModal: false,
      acaoModalConfirmada: null,
      showPopup: false,
      popupMessage: '',
      popupType: 'success'
    };
  },
  computed: {
    funcionariosFiltrados() {
      return this.funcionarios
        .filter(funcionario =>
          funcionario.nome.toLowerCase().includes(this.searchQuery.toLowerCase())
        )
        .sort((a, b) => {
          return (a.despachado === b.despachado) ? 0 : a.despachado ? 1 : -1;
        });
    }
  },
  mounted() {
    this.carregarFuncionarios();
  },
  methods: {
    async carregarFuncionarios() {
      try {
        const response = await FuncionarioService.listarTodos();
        this.funcionarios = response.data;
        if (!this.funcionarioEmEdicao && this.funcionarios.length > 0) {
          this.funcionarioEmEdicao = { ...this.funcionarios[0] };
        }
      } catch (error) {
        console.error('Erro ao carregar funcionários:', error);
      }
    },
    iniciarEdicao(funcionario) {
      this.funcionarioEmEdicao = { ...funcionario };
    },
    async atualizarFuncionario() {
      try {
        await FuncionarioService.atualizar(this.funcionarioEmEdicao);
        this.carregarFuncionarios();
        // Exibe popup de sucesso
        this.popupMessage = 'Funcionário atualizado com sucesso!';
        this.popupType = 'success';
        this.showPopup = true;
      } catch (error) {
        console.error('Erro ao atualizar funcionário:', error);
        // Exibe popup de erro
        this.popupMessage = 'Erro ao atualizar funcionário.';
        this.popupType = 'error';
        this.showPopup = true;
      }
    },
    async despacharFuncionario() {
      try {
        this.funcionarioEmEdicao.despachado = true;
        await FuncionarioService.atualizar(this.funcionarioEmEdicao);
        this.carregarFuncionarios();
      } catch (error) {
        console.error('Erro ao despachar funcionário:', error);
      }
    },
    async cancelarDespachoFuncionario() {
      try {
        this.funcionarioEmEdicao.despachado = false;
        await FuncionarioService.atualizar(this.funcionarioEmEdicao);
        this.carregarFuncionarios();
      } catch (error) {
        console.error('Erro ao cancelar despacho do funcionário:', error);
      }
    },
    async excluirFuncionario() {
      this.showModal = true;
      this.acaoModalConfirmada = async () => {
        try {
          await FuncionarioService.excluir(this.funcionarioEmEdicao.id);
          this.funcionarioEmEdicao = null;
          this.carregarFuncionarios();
          this.showModal = false;
        } catch (error) {
          console.error('Erro ao excluir funcionário:', error);
        }
      };
    }
  }
};
</script>

<style>
/* Barra de rolagem suave, fina e discreta */
.barra-customizada {
  scrollbar-width: thin;
  scrollbar-color: #888 transparent;
}
.barra-customizada::-webkit-scrollbar {
  width: 6px;
}
.barra-customizada::-webkit-scrollbar-track {
  background: transparent;
}
.barra-customizada::-webkit-scrollbar-thumb {
  background-color: #888;
  border-radius: 4px;
  transition: background-color 0.3s ease;
}
.barra-customizada:hover::-webkit-scrollbar-thumb {
  background-color: #aaa;
}

/* Estilo de fade para transições */
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s;
}
.fade-enter,
.fade-leave-to {
  opacity: 0;
}
</style>
