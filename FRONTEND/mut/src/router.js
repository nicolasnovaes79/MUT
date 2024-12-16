import { createRouter, createWebHistory } from 'vue-router';
import PageForm from './assets/pages/PageForm.vue';

const routes = [
 { path: '/page_formulario', component: PageForm },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
