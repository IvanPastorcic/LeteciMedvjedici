import { defineConfig } from 'vite';

export default defineConfig({
  server: {
    proxy: {
      '/safebear': 'http://localhost:8081/safebear-backend',  // Make sure this matches your backend API's URL
    },
  },
});
