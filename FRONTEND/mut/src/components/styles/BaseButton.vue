<!-- BaseButton.vue -->
<template>
    <button 
      :type="type" 
      :class="computedClasses"
      @click="$emit('click')"
    >
      <slot></slot>
    </button>
  </template>
  
  <script>
  export default {
    name: 'BaseButton',
    props: {
      type: {
        type: String,
        default: 'button'
      },
      variant: {
        type: String,
        default: 'primary' // Padrão é um botão azul
      },
      size: {
        type: String,
        default: 'md' // Tamanho padrão médio
      }
    },
    computed: {
      computedClasses() {
        const base = "font-medium rounded-lg text-center focus:outline-none focus:ring-4";
        const sizes = {
          sm: "text-xs px-3 py-1.5",
          md: "text-sm px-5 py-2.5",
          lg: "text-lg px-6 py-3"
        };
        const variants = {
          primary: "text-white bg-sky-700 hover:bg-sky-800 focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800",
          secondary: "text-gray-900 bg-gray-200 hover:bg-gray-300 focus:ring-gray-400 dark:bg-gray-600 dark:hover:bg-gray-700 dark:focus:ring-gray-800",
          danger: "text-white bg-red-600 hover:bg-red-700 focus:ring-red-300 dark:bg-red-500 dark:hover:bg-red-600 dark:focus:ring-red-700"
        };
  
        return `${base} ${sizes[this.size]} ${variants[this.variant]}`;
      }
    }
  };
  </script>
  