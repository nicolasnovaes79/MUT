<template>
    <transition name="fade">
      <div v-if="visible" class="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50">
        <div
          class="bg-white dark:bg-gray-900 p-8 rounded-2xl shadow-2xl max-w-sm w-full text-center border border-green-400"
        >
          <!-- Ícone de check animado -->
          <div class="flex justify-center mb-4">
            <div class="bg-green-100 dark:bg-green-900 p-4 rounded-full">
              <svg
                class="w-10 h-10 text-green-600 animate-pulse"
                fill="none"
                stroke="currentColor"
                stroke-width="2"
                viewBox="0 0 24 24"
              >
                <path stroke-linecap="round" stroke-linejoin="round" d="M5 13l4 4L19 7" />
              </svg>
            </div>
          </div>
  
          <h2 class="text-2xl font-bold text-green-600 mb-2">Sucesso!</h2>
          <p class="text-gray-700 dark:text-gray-300 mb-6">{{ message }}</p>
  
          <button
            @click="fechar"
            class="px-5 py-2.5 bg-green-600 hover:bg-green-700 text-white font-medium rounded-lg transition duration-300 shadow-md"
          >
            OK
          </button>
        </div>
      </div>
    </transition>
  </template>
  
  <script>
  export default {
    name: "ModalSucesso",
    props: {
      message: {
        type: String,
        default: "Ação realizada com sucesso!",
      },
      show: {
        type: Boolean,
        required: true,
      },
    },
    data() {
      return {
        visible: this.show,
      };
    },
    watch: {
      show(val) {
        this.visible = val;
      },
    },
    methods: {
      fechar() {
        this.visible = false;
        this.$emit("close");
      },
    },
  };
  </script>
  
  <style scoped>
  .fade-enter-active,
  .fade-leave-active {
    transition: opacity 0.3s ease;
  }
  .fade-enter-from,
  .fade-leave-to {
    opacity: 0;
  }
  </style>
  