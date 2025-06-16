<template>
  <header class="bg-light border-bottom shadow-sm py-2">
    <div class="container d-flex justify-content-between align-items-center">
      <!-- Logo with image and router-link -->
      <router-link to="/" class="text-decoration-none">
        <img src="@/assets/logo.jpg" alt="ShopEasy Logo" height="60"/>
      </router-link>

      <!-- Navigation -->
      <nav>
        <ul class="nav">
          <li class="nav-item">
            <router-link v-if="!isLoggedIn" to="#" class="nav-link" @click.prevent="login">
              Login
            </router-link>
            <router-link v-else to="/cart" class="nav-link">
              Cart
            </router-link>
          </li>
        </ul>
      </nav>
    </div>
  </header>
</template>

<script>
export default {
  name: 'Header',
  data() {
    return {
      isLoggedIn: false
    }
  },
  methods: {
    login() {
      // Simulate login success
      this.isLoggedIn = true
    }
  }
}
</script>

<style scoped>
.nav-link {
  font-weight: 500;
  cursor: pointer;
}
</style>
