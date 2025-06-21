<template>
  <div>
    <h2 class="mb-4">🛒 All Products</h2>

    <div class="row g-3">
      <div class="col-6 col-md-3" v-for="item in products" :key="item.id">
        <div class="card h-100">
          <!-- Image Preview -->
          <img
              v-if="item.anhGiay?.length > 0"
              :src="item.anhGiay[0].anh.duongDan"
              class="card-img-top"
              alt="Ảnh sản phẩm"
              style="height: 150px; object-fit: cover;"
          />
          <div v-else class="bg-secondary" style="height: 150px;"></div>

          <div class="card-body d-flex flex-column">
            <h5 class="card-title">{{ item.tenGiay }}</h5>
            <p class="card-text text-muted">{{ item.moTaGiay }}</p>
            <div class="mt-auto">
              <button class="btn btn-primary w-100">Add to Cart</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Products',
  data() {
    return {
      products: [],
    }
  },
  methods: {
    async fetchProducts() {
      try {
        const response = await axios.get('/giay')
        this.products = response.data.content // Adjust if structure is different
      } catch (error) {
        console.error('❌ Failed to load products:', error)
      }
    }
  },
  mounted() {
    this.fetchProducts()
  },
}
</script>

