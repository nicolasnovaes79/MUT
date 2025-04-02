// src/utils/httpUtil.js
import axios from 'axios';

const http = axios.create({
  baseURL: 'http://localhost:8080/api',
  headers: {
    'Content-Type': 'application/json'
  }
});

// Interceptador de requisições (se quiser adicionar token JWT, por exemplo)
http.interceptors.request.use(config => {
  // const token = localStorage.getItem('token');
  // if (token) {
  //   config.headers.Authorization = `Bearer ${token}`;
  // }
  return config;
}, error => {
  return Promise.reject(error);
});

// Interceptador de respostas (tratamento global de erro)
http.interceptors.response.use(response => {
  return response;
}, error => {
  console.error('Erro na requisição:', error.response?.data || error.message);
  return Promise.reject(error);
});

export default http;
