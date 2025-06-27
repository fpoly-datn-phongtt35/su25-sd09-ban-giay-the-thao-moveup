<template>
  <header class="bg-light border-bottom shadow-sm py-2 sticky-top">
    <div class="container d-flex justify-content-between align-items-center">
      <!-- Logo -->
      <router-link to="/" class="text-decoration-none">
        <img src="@/assets/logo.jpg" alt="ShopEasy Logo" height="60"/>
      </router-link>

      <!-- Navigation -->
      <nav>
        <ul class="nav">
          <li class="nav-item">
            <router-link to="/giay" class="nav-link">
              Products
            </router-link>
          </li>
          <li class="nav-item">
            <router-link v-if="!isLoggedIn" to="#" class="nav-link" @click.prevent="login">
              <font-awesome-icon icon="right-to-bracket" />
            </router-link>
            <!-- Show cart modal button -->
            <cart-modal v-else></cart-modal>
          </li>
        </ul>
      </nav>
    </div>
  </header>
</template>

<script>
import CartModal from "@/client/components/CartModal.vue";

export default {
  components: {
    CartModal
  },
  data() {
    return {
      isLoggedIn: false,
    }
  },
  methods: {
    login() {
      // Simulate login success
      this.isLoggedIn = true
    },
  }
}
</script>

<style scoped>
.nav-link {
  font-weight: 500;
  cursor: pointer;
}
</style>
