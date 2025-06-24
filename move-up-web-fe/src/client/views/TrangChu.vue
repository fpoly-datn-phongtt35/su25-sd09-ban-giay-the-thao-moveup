<template>
  <div>
    <!-- 🖼 Banner -->
    <section class="bg-secondary text-white text-center p-5 rounded mb-4">
      <h2>🎉 Big Sale This Week!</h2>
      <p>Don’t miss out on our hottest deals — shop now!</p>
    </section>

    <!-- 🗂 Categories -->
    <section class="mb-5">
      <h3 class="mb-3">🛍️ Shop by Category</h3>
      <div class="row g-3">
        <div class="col-6 col-md-3" v-for="n in 4" :key="'cat-' + n">
          <div class="border rounded p-3 text-center bg-light">
            <!-- Replace with actual image -->
            <div class="mb-2 bg-secondary" style="height: 100px;"></div>
            <strong>Category {{ n }}</strong>
          </div>
        </div>
      </div>
    </section>

    <!-- ⭐ Featured Products -->
    <section class="mb-5">
      <h3 class="mb-3">⭐ Featured Products</h3>
      <div class="row g-3">
        <div class="col-6 col-md-3" v-for="n in 4" :key="'feat-' + n">
          <div class="card h-100">
            <!-- Image placeholder -->
            <div class="bg-secondary" style="height: 150px;"></div>
            <div class="card-body">
              <h5 class="card-title">Product {{ n }}</h5>
              <p class="card-text text-muted">Short description here.</p>
              <button class="btn btn-primary w-100">Add to Cart</button>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- 📢 Promo Banner -->
    <section class="bg-warning text-center p-4 rounded mb-5">
      <h4>📢 Limited Time Offer</h4>
      <p>Get free shipping on orders over $50!</p>
    </section>

    <!-- 📬 Newsletter Signup -->
    <section class="bg-light p-4 rounded text-center">
      <h5>📬 Subscribe to our newsletter</h5>
      <p class="mb-3">Get updates on new arrivals and special offers.</p>
      <div class="d-flex justify-content-center">
        <input
            type="email"
            class="form-control w-50 me-2"
            placeholder="Enter your email"
        />
        <button class="btn btn-success">Subscribe</button>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  name: 'Home'
}
</script>
