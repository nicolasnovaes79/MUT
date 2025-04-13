import { createRouter, createWebHistory } from 'vue-router';
import PageForm from './assets/pages/PageForm.vue';
import GestorPage from './assets/pages/GestorPage.vue';
import PageConfiguracoes from './assets/pages/PageConfiguracoes.vue';
import PageRegistrosDespachados from './assets/pages/PageRegistrosDespachados.vue';
import LoginPage from './assets/pages/LoginPage.vue'; // Importar o LoginPage.vue
import RegistrarPage from './assets/pages/RegistrarPage.vue'; // Importar o RegisterPage.vue

const routes = [
  { path: '/', component: LoginPage }, // Página inicial vai direto para o login
  { path: '/register', component: RegistrarPage }, // Rota para a página de registro
  { path: '/page_formulario', component: PageForm },
  { path: '/gestor_registros', component: GestorPage },
  { path: '/configuracoes', component: PageConfiguracoes },
  { path: '/despachados', component: PageRegistrosDespachados },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
