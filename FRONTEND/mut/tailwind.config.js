/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    './index.html', 
    './src/**/*.{vue,js,ts,jsx,tsx}',  // Inclua todos os arquivos Vue e JS/TS no diretório src
  ],
  theme: {
    extend: {
      fontFamily: {
        'dancing': ['"Dancing Script"', 'cursive'], // Adiciona a fonte Dancing Script
      },
    },
  },
  plugins: [],
};
