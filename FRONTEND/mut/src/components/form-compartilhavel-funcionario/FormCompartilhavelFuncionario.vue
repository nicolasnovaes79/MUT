<template>
    <form
      class="max-w-md mx-auto mt-8 p-6 bg-gray-800 rounded-lg shadow-lg"
      @submit.prevent="submitForm"
    >
      <img style="height: 12vh; width: 12vh; border-radius: 100%;"
        src="../../assets/img/mUT (2).png"
        alt="Imagem decorativa"
        class="mx-auto mb-4 w-20 h-auto"
      />
  
      <h1 class="mb-6 text-4xl font-bold text-center text-sky-400">
        <span class="bg-clip-text text-transparent bg-gradient-to-r from-sky-500 to-sky-300">
          Formulário
        </span>
        Mut
      </h1>
  
      <transition name="fade" mode="out-in">
        <div :key="section">
          <!-- Seção 1 -->
          <div v-show="section === 1">
            <BaseInput
              v-if="configuracao.nome"
              label="Nome"
              id="name"
              type="text"
              placeholder="Nome Exemplo"
              v-model="formData.nome"
              required
            />
            <BaseInput
              v-if="configuracao.cpf"
              label="CPF"
              id="cpf"
              type="text"
              placeholder="000.000.000-00"
              v-model="formData.cpf"
              @input="applyMaskCPF"
              required
            />
            <BaseInput
              v-if="configuracao.email"
              label="Email"
              id="email"
              type="email"
              placeholder="email@exemplo.com"
              v-model="formData.email"
              required
            />
          </div>
  
          <!-- Seção 2 -->
          <div v-show="section === 2">
            <BaseInput
              v-if="configuracao.telefone"
              label="Telefone"
              id="telefone"
              type="text"
              placeholder="(00) 00000-0000"
              v-model="formData.telefone"
              @input="applyMaskPhone"
              required
            />
            <BaseInput
              v-if="configuracao.cargo"
              label="Cargo"
              id="cargo"
              type="text"
              placeholder="Cargo do Funcionário"
              v-model="formData.cargo"
              required
            />
            <BaseInput
              v-if="configuracao.salario"
              label="Salário"
              id="salario"
              type="number"
              placeholder="5000.00"
              v-model="formData.salario"
              required
              min="0"
            />
            <BaseInput
              v-if="configuracao.dataAdmissao"
              label="Data de Admissão"
              id="dataAdmissao"
              type="date"
              v-model="formData.dataAdmissao"
              required
            />
            <div class="flex items-start mb-5" v-if="configuracao.ativo">
              <div class="flex items-center h-5">
                <input
                  id="ativo"
                  type="checkbox"
                  v-model="formData.ativo"
                  class="w-4 h-4 border border-gray-600 rounded bg-gray-700 focus:ring-3 focus:ring-sky-300"
                />
              </div>
              <label for="ativo" class="ml-2 text-sm text-gray-300">
                Funcionário Ativo
              </label>
            </div>
          </div>
        </div>
      </transition>
  
      <!-- Botões de navegação -->
      <div class="flex gap-4 justify-center mt-6">
        <BaseButton
          type="button"
          variant="primary"
          size="md"
          v-if="section > 1"
          @click="voltarSection"
        >
          Voltar
        </BaseButton>
  
        <BaseButton
          v-if="section === 1"
          type="button"
          variant="primary"
          size="md"
          @click="proximaSection"
        >
          Próximo
        </BaseButton>
  
        <BaseButton
          v-else-if="section === 2"
          type="submit"
          variant="primary"
          size="md"
        >
          Enviar
        </BaseButton>
      </div>

    </form>

    <ModalSucesso
    v-if="showModalSucesso"
    :show="showModalSucesso"
    message="Funcionário cadastrado com sucesso!"
    @close="showModalSucesso = false"
  />
</template>

<script>
import BaseInput from "../styles/BaseInput.vue";
import BaseButton from "../styles/BaseButton.vue";
import ModalSucesso from "../modais/ModalSucesso.vue";
import FuncionarioService from "../../services/FuncionarioService";
import ConfiguracaoService from "../../services/ConfiguracaoService";
import EmpresaService from "../../services/EmpresaService";

export default {
  name: "FormCompartilhavelFuncionario",
  components: { BaseInput, BaseButton, ModalSucesso },
  data() {
    return {
      section: 1,
      formData: {
        nome: "",
        cpf: "",
        email: "",
        telefone: "",
        cargo: "",
        salario: null,
        dataAdmissao: "",
        ativo: true,
      },
      showModalSucesso: false,
      idEmpresa: null,
      configuracao: {},
    };
  },
  created() {
    this.carregarConfiguracoes();
    this.token = this.$route.params.token;
    this.idEmpresa = this.$route.params.idEmpresa;
    this.consultarEmpresaPorId(this.idEmpresa);
 },
  methods: {
    // Carregar as configurações do backend
    async carregarConfiguracoes() {
      try {
        const response = await ConfiguracaoService.listarTodos();
        this.configuracao = response.data;
      } catch (error) {
        console.error("Erro ao carregar configurações:", error);
      }
    },
    applyMaskCPF(event) {
      let value = event.target.value.replace(/\D/g, "");
      value = value.replace(/(\d{3})(\d)/, "$1.$2");
      value = value.replace(/(\d{3})(\d)/, "$1.$2");
      value = value.replace(/(\d{3})(\d{1,2})$/, "$1-$2");
      this.formData.cpf = value.slice(0, 14);
    },
    applyMaskPhone(event) {
      let value = event.target.value.replace(/\D/g, "");
      value = value.replace(/(\d{2})(\d)/, "($1) $2");
      value = value.replace(/(\d{5})(\d{1,4})$/, "$1-$2");
      this.formData.telefone = value.slice(0, 15);
    },
    // Método para consultar a empresa pelo ID
    async consultarEmpresaPorId(id) {
      try {
        const response = await EmpresaService.getPorId(id);
        const empresa = response.data;

        // Atualizando os dados da empresa no formData
        this.formData.empresa = empresa;
        console.log("Dados da empresa:", empresa);
      } catch (error) {
        console.error("Erro ao consultar os dados da empresa:", error);
      }
    },
    async submitForm() {
      try {
        const payload = {
          ...this.formData,
        };

        const response = await FuncionarioService.salvar(payload);
        console.log("Funcionário registrado com sucesso:", response.data);
        this.showModalSucesso = true;
      } catch (error) {
        console.error(
          "Erro ao registrar funcionário:",
          error.response?.data || error.message
        );
      }
    },
    proximaSection() {
      if (this.section < 2) this.section++;
    },
    voltarSection() {
      if (this.section > 1) this.section--;
    },
 }
};
</script>

<style scoped>
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s ease;
}
.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>
