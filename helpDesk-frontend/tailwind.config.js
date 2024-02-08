module.exports = {
  content: ["./src/**/*.{html,ts}"],
  theme: {
    extend: {
      colors: {
        'custom-fundo' : 'rgb(229, 231, 235)',
        'custom-bloco' : '#1685A4',
        'custom-botao' : '#00425A',
        'custom-span' : '#AFD3E2',
        'custom-font-destaque' : '#FFFFFF',
        'custom-font-secundario' : '#F6F1F1'
      },
      fontFamily: {
        'font-primaria': ['Roboto', 'sans-serif'],
        'font-secundaria': ['Poppins', 'sans-serif'],
      },
      screens: {
        'auto': {'max' : '1980px'},
        'desktop2': { 'max': '1300px' },
        'desktop': { 'max': '1280px' },
        'laptop': {'max': '1024px'},
        'tablet': {'max': '640px'},
        'sm': {'max': '360px'},
      },
    },
  },
  plugins: [],
}


