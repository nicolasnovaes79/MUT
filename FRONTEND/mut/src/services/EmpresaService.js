import http from '@/utils/httpUtil';

export default {
  getIdPorLogin(login) {
    return http.get(`/empresas/${login}/id`);
  },

  // Novo método: consulta os dados da empresa pelo ID
  getPorId(id) {
    return http.get(`/empresas/${id}`);
  }
};
