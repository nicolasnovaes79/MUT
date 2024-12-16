<!-- src/components/Navbar.vue -->
<template>
    <nav class="border-gray-200 dark:bg-gray-800 dark:border-gray-700" id="nav">
      <div class="max-w-screen-xl flex flex-wrap items-center justify-between mx-auto p-4">
        <img src="../assets/img/logo.png" class="logo h-10" alt="Logo" />
  
        <div class="hidden w-full md:block md:w-auto" id="navbar-solid-bg">
          <ul class="flex flex-col font-medium mt-4 rounded-lg bg-gray-50 md:space-x-8 rtl:space-x-reverse md:flex-row md:mt-0 md:border-0 md:bg-transparent dark:bg-gray-800 md:dark:bg-transparent dark:border-gray-700">
            <li>
              <RouterLink to="/" class="nav-link">
                <i class="fa-solid fa-truck-fast"></i> Registros prontos
              </RouterLink>
            </li>
            <li>
              <RouterLink to="/index_dp_fiscal" class="nav-link">
                <i class="fa-solid fa-paper-plane"></i> Registros a preencher
              </RouterLink>
            </li>
            <li>
              <RouterLink to="/page_formulario" class="nav-link">
                <i class="fa-solid fa-handshake"></i> Formulário
              </RouterLink>
            </li>
            <li>
              <RouterLink to="/dashboard" class="nav-link">
                <i class="fa-solid fa-mug-hot"></i> Para aprovar
              </RouterLink>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </template>
  
  <script>
  export default {
    name: "NavBar"
  };
  </script>
  
  <style scoped>
  #nav {
    background-color: #004d80;
    color: white;
  }
  
  .nav-link {
    display: block;
    padding: 0.5rem 1rem;
    color: white;
    border-radius: 0.5rem;
    transition: background-color 0.3s, color 0.3s;
  }
  
  .nav-link:hover {
    background-color: rgb(29, 41, 56);
    color: #38bdf8; /* Sky Blue */
  }
  
  .logo {
    transform: scale(1.8);
    align-self: center;
    pointer-events: none;
    margin-left: 12px;
  }
  </style>
  