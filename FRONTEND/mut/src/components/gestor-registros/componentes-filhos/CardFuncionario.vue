<template>
  <div
    :class="[
      'max-w-md p-8 bg-gray-800 border-gray-700 shadow-sm transition-all cursor-pointer mb-2',
      { 'border-blue-500 border-4 ring-2 ring-blue-200': selected }
    ]"
    style="margin-left: 2vh;"
    @click="editFuncionario"
  >
    <!-- Etiqueta de despachado ou não despachado no topo -->
    <div class="mb-2">
      <span
        v-if="funcionario.despachado"
        class="inline-flex items-center px-3 py-1 text-xs text-white bg-sky-600"
      >
        Despachado
      </span>
      <span
        v-else
        class="inline-flex items-center px-3 py-1 text-xs text-white bg-red-500"
      >
        Não Despachado
      </span>
    </div>

    <h5
      class="mb-2 text-2xl font-bold tracking-tight text-white truncate"
      :title="funcionario.nome"
    >
      {{ funcionario.nome }}
    </h5>
    <p class="mb-3 font-normal text-gray-300">
      {{ funcionario.cargo }}
    </p>
  </div>
</template>

<script>
export default {
  name: 'CardFuncionario',
  props: {
    funcionario: {
      type: Object,
      required: true
    },
    selected: {
      type: Boolean,
      default: false
    }
  },
  methods: {
    editFuncionario() {
      this.$emit('edit', this.funcionario);
    }
  }
};
</script>
