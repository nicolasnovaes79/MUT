import http from '@/utils/httpUtil';

export default {
  listarTodos() {
    return http.get('/funcionarios');
  },
  
  listarFuncionariosDespachados() {
    return http.get('/funcionarios/despachados');
  },
  
  salvar(funcionario) {
    return http.post('/funcionarios', funcionario);
  },

  atualizar(funcionario) {
    return http.put(`/funcionarios/${funcionario.id}`, funcionario);
  },

  excluir(id) {
    return http.delete(`/funcionarios/${id}`);
  },

};
