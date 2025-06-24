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
            <h5 class="card-title">
              <router-link :to="`/product/${item.id}`" class="product-link">
                {{ item.tenGiay }}
              </router-link>
            </h5>
            <p class="card-text text-muted">{{ getPriceRange(item) }}</p>
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
        this.products = response.data.content
      } catch (error) {
        console.error('❌ Failed to load products:', error)
      }
    },
    getPriceRange(item) {
      const prices = item.chiTietGiay?.map(ct => ct.giaBan).filter(p => typeof p === 'number')
      if (!prices || prices.length === 0) return 'Chưa có giá'

      const min = Math.min(...prices)
      const max = Math.max(...prices)

      if (min === max) return `${min.toLocaleString()}₫`
      return `${min.toLocaleString()}₫ ~ ${max.toLocaleString()}₫`
    }
  },
  mounted() {
    this.fetchProducts()
  },
}
</script>

<style scoped>
.product-link {
  color: #212529; /* Bootstrap's text-dark */
  text-decoration: none;
  transition: color 0.2s;
}

.product-link:hover {
  color: #0d6efd; /* Bootstrap's primary blue */
}
</style>


