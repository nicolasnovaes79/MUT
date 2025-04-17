import { createRouter, createWebHistory } from 'vue-router';
import PageForm from './assets/pages/PageForm.vue';
import GestorPage from './assets/pages/GestorPage.vue';
import PageConfiguracoes from './assets/pages/PageConfiguracoes.vue';
import PageRegistrosDespachados from './assets/pages/PageRegistrosDespachados.vue';
import LoginPage from './assets/pages/LoginPage.vue';
import RegistrarPage from './assets/pages/RegistrarPage.vue';
import PageFormCompartilhavelFuncionario from './assets/pages/PageFormCompartilhavelFuncionario.vue';

const routes = [
  { path: '/', component: LoginPage },
  { path: '/registrar', component: RegistrarPage },
  { path: '/page_formulario', component: PageForm },
  { path: '/gestor_registros', component: GestorPage },
  { path: '/configuracoes', component: PageConfiguracoes },
  { path: '/despachados', component: PageRegistrosDespachados },
  
  // Rota para o formulário compartilhado
  { path: '/formulario/:token/:idEmpresa', component: PageFormCompartilhavelFuncionario, props: true },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
