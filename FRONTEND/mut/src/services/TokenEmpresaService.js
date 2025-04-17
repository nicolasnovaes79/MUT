import http from '@/utils/httpUtil';

export default {
  // Método para gerar um novo token para uma empresa
  gerarToken(empresaId) {
    return http.post(`/token-empresa/gerar/${empresaId}`)
      .then(response => response.data)
      .catch(error => {
        console.error("Erro ao gerar token: ", error);
        throw error;
      });
  },

  // Método para validar um token
  validarToken(token) {
    return http.get(`/token-empresa/validar/${token}`)
      .then(response => response.data)
      .catch(error => {
        console.error("Erro ao validar token: ", error);
        throw error;
      });
  },

  // Método para buscar o token por seu valor
  buscarPorToken(token) {
    return http.get(`/token-empresa/${token}`)
      .then(response => response.data)
      .catch(error => {
        console.error("Erro ao buscar token: ", error);
        throw error;
      });
  },

  consultarTokenPorEmpresa(empresaId) {
    return http.get(`/token-empresa/consultar/${empresaId}`)
      .then(response => response.data)
      .catch(error => {
        console.error("Erro ao consultar token por empresa: ", error);
        throw error;
      });
  }
};
