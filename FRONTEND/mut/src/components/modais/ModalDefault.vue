<template>
  <!-- Modal Overlay -->
  <transition name="fade">
    <div
      v-if="isVisible"
      class="fixed inset-0 bg-black/40 backdrop-blur-sm flex justify-center items-center z-50"
    >
      <!-- Modal Content -->
      <transition name="scale-fade">
        <div class="bg-white rounded-2xl shadow-xl w-full max-w-md p-6">
          <!-- Modal Header -->
          <div class="flex justify-between items-center border-b pb-3 mb-4">
            <h3 class="text-xl font-bold text-gray-800">Excluir Funcionário</h3>
            <button @click="closeModal" class="text-gray-400 hover:text-gray-600 transition">
              <svg xmlns="http://www.w3.org/2000/svg" class="w-6 h-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                  d="M6 18L18 6M6 6l12 12" />
              </svg>
            </button>
          </div>

          <!-- Modal Body -->
          <p class="text-gray-600 mb-6 text-sm leading-relaxed">
            Tem certeza que deseja excluir este funcionário? Esta ação não poderá ser desfeita.
          </p>

          <!-- Modal Footer -->
          <div class="flex justify-end gap-3">
            <button
              @click="closeModal"
              class="px-4 py-2 text-sm text-gray-700 bg-gray-200 rounded-lg hover:bg-gray-300 transition"
            >
              Cancelar
            </button>
            <button
              @click="confirmDeletion"
              class="px-4 py-2 text-sm text-white bg-red-600 rounded-lg hover:bg-red-700 transition"
            >
              Excluir
            </button>
          </div>
        </div>
      </transition>
    </div>
  </transition>
</template>

<script>
export default {
  name: 'ModalExcluirFuncionario',
  props: {
    isVisible: {
      type: Boolean,
      required: true
    }
  },
  methods: {
    closeModal() {
      this.$emit('update:isVisible', false);
    },
    confirmDeletion() {
      this.$emit('confirm');
      this.closeModal();
    }
  }
};
</script>

<style scoped>
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.25s ease;
}
.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}

.scale-fade-enter-active {
  transition: all 0.3s ease;
}
.scale-fade-enter-from {
  opacity: 0;
  transform: scale(0.95);
}
.scale-fade-leave-active {
  transition: all 0.2s ease;
}
.scale-fade-leave-to {
  opacity: 0;
  transform: scale(0.95);
}
</style>
