import http from '@/utils/httpUtil';

export default {
  listarTodos() {
    return http.get('/configuracoes');
  },
  salvar(configuracao) {
    return http.post('/configuracoes', configuracao);
  },
  atualizar(configuracao) {
    return http.put(`/configuracoes/${configuracao.id}`, configuracao);
  },
  excluir(id) {
    return http.delete(`/configuracoes/${id}`);
  }
};
