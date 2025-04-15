import http from '@/utils/httpUtil';

export default {
  // Modificar para incluir o login na URL
  getIdPorLogin(login) {
    return http.get(`/empresas/${login}/id`);
  }
};
