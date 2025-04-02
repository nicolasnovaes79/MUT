import http from '@/utils/httpUtil';

export default {
  // Listar todos os funcionários
  listarTodos() {
    return http.get('/funcionarios');
  },
  
  // Listar apenas os funcionários despachados (despachado = true)
  listarFuncionariosDespachados() {
    return http.get('/funcionarios/despachados');
  },
  
  // Salvar um novo funcionário
  salvar(funcionario) {
    return http.post('/funcionarios', funcionario);
  },

  // Atualizar informações de um funcionário
  atualizar(funcionario) {
    return http.put(`/funcionarios/${funcionario.id}`, funcionario);
  },

  // Excluir um funcionário
  excluir(id) {
    return http.delete(`/funcionarios/${id}`);
  }
};
