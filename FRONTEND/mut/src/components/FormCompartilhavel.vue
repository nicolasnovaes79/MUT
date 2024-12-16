<template>
    <form class="max-w-sm mx-auto mt-6 p-2" @submit.prevent="submitForm">
      
     <!-- Imagem decorativa -->
    <img 
      src="../assets/img/GIU AMA 255-08.png" 
      alt="Imagem decorativa"
      class="mx-auto mb-2 w-16 h-auto"
    />

      <h1 class="mb-4 text-5xl font-extrabold text-gray-900 text-white md:text-5xl lg:text-5xl">
  <span class="text-transparent bg-clip-text bg-gradient-to-r to-sky-600 from-sky-400 p-1">Formulário</span>Mut
</h1>

<br>
      <BaseInput 
        label="Nome" 
        id="name" 
        type="text" 
        placeholder="Nome Exemplo" 
        v-model="formData.name"
        required
      />
      <BaseInput 
        label="CPF" 
        id="cpf" 
        placeholder="999.999.999-99" 
        type="text"
        v-model="formData.cpf"
        required
      />
      <BaseInput 
        label="Título Eleitor" 
        id="eleitor" 
        type="text"
        v-model="formData.eleitor"
        required
      />
      <BaseInput 
        label="Logradouro" 
        id="logradouro" 
        type="text" 
        placeholder="Rua B" 
        v-model="formData.logradouro"
        required
      />
      <div class="flex items-start mb-5">
        <div class="flex items-center h-5">
          <input id="terms" type="checkbox" v-model="formData.terms" class="w-4 h-4 border border-gray-300 rounded bg-gray-50 focus:ring-3 focus:ring-sky-300 dark:bg-gray-700 dark:border-gray-600 dark:focus:ring-sky-600 dark:ring-offset-gray-800 dark:focus:ring-offset-gray-800" required />
        </div>
        <label for="terms" class="ms-2 text-sm font-medium text-gray-900 text-gray-300">Concordo com os <a href="#" class="text-sky-600 hover:underline dark:text-sky-500">termos e condições</a></label>
      </div>

  <div class="flex gap-4">
  <BaseButton 
    type="button" 
    variant="primary" 
    size="md"
  >
    Voltar
  </BaseButton>
  
  <BaseButton 
    type="button" 
    variant="primary" 
    size="md"
  >
    Próximo
  </BaseButton>
</div>



    </form>
  </template>
  
  <script>
  import BaseInput from '../components/styles/BaseInput.vue';
  import BaseButton from '../components/styles/BaseButton.vue';
  
  export default {
    name: 'FormCompartilhavel',
    components: {
      BaseInput,
      BaseButton
    },
    data() {
      return {
        formData: {
          name: '',
          cpf: '',
          eleitor: '',
          logradouro: '',
          numero: '',
          bairro: '',
          terms: false
        }
      };
    },
    methods: {
      submitForm() {
        // Lógica para enviar o formulário
        console.log(this.formData);
      }
    }
  };
  </script>
  