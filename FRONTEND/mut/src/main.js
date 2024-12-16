import { createApp } from 'vue';
import App from './App.vue';

import './assets/css/tailwind.css'; // Importando o Tailwind CSS
import '@fortawesome/fontawesome-free/css/all.css'; // Importando o FontAwesome
import router from './router'; // Importando o Vue Router

// Cria a instância do Vue e aplica o Vue Router
const app = createApp(App);

app.use(router); // Adicionando o Vue Router à aplicação
app.mount('#app'); // Monta a aplicação no elemento com o id 'app'
