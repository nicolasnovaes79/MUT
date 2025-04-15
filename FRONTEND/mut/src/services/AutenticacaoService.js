import http from '@/utils/httpUtil';

export default {
  login(loginData) {
    return http.post('/auth/login', loginData);
  },

  registrar(registrarData) {
    return http.post('/auth/registrar', registrarData);
  }
};
