<template>
  <div>
    <Header />
    <main class="container py-4">
      <router-view />
    </main>
    <Footer />
  </div>
</template>

<script>
import Header from '../components/Header.vue'
import Footer from '../components/Footer.vue'

export default {
  name: 'ClientLayout',
  components: {
    Header,
    Footer
  }
}
</script>
