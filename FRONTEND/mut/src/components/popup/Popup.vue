<template>
    <transition name="fade">
      <div v-if="visible" :class="['popup', typeClass]">
        <p>{{ message }}</p>
        <button class="close" @click="close">&times;</button>
      </div>
    </transition>
  </template>
  
  <script>
  export default {
    name: 'PopupMessage',
    props: {
      message: {
        type: String,
        required: true
      },
      type: {
        type: String,
        default: 'success', // 'success' ou 'error'
        validator: value => ['success', 'error'].includes(value)
      },
      duration: {
        type: Number,
        default: 3000 // tempo em milissegundos antes de fechar automaticamente
      }
    },
    data() {
      return {
        visible: true
      };
    },
    computed: {
      typeClass() {
        return this.type === 'success' ? 'popup-success' : 'popup-error';
      }
    },
    mounted() {
      if (this.duration > 0) {
        setTimeout(() => {
          this.close();
        }, this.duration);
      }
    },
    methods: {
      close() {
        this.visible = false;
        this.$emit('close');
      }
    }
  };
  </script>
  
  <style scoped>
  .popup {
    position: fixed;
    bottom: 20px;           /* Posição no rodapé */
    left: 50%;              /* Centralizado horizontalmente */
    transform: translateX(-50%);
    padding: 0.5rem 1rem;    /* Menos espaço interno */
    border-radius: 3px;
    color: #fff;
    font-size: 0.9rem;      /* Fonte um pouco menor */
    display: flex;
    align-items: center;
    justify-content: space-between;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* Sombra mais suave */
    z-index: 1000;
  }
  .popup-success {
    background-color: #38bdf8; /* Azul para sucesso */
  }
  .popup-error {
    background-color: #f87171; /* Vermelho para erro */
  }
  .close {
    background: transparent;
    border: none;
    color: #fff;
    font-size: 1rem;
    cursor: pointer;
    margin-left: 0.5rem;
  }
  
  /* Transição fade */
  .fade-enter-active,
  .fade-leave-active {
    transition: opacity 0.5s;
  }
  .fade-enter,
  .fade-leave-to {
    opacity: 0;
  }
  </style>
  