<template>
  <div v-if="product" class="container py-4">
    <h2 class="mb-3">{{ product.tenGiay }}</h2>

    <!-- Info Row -->
    <div class="row mb-4">
      <!-- Left: Images -->
      <div class="col-md-5">
        <img
            v-if="product.anhGiay?.length > 0"
            :src="product.anhGiay[0].anh.duongDan"
            class="img-fluid rounded shadow-sm"
            alt="Ảnh sản phẩm"
        />
        <div v-else class="bg-secondary text-white text-center py-5 rounded">
          Không có ảnh
        </div>
      </div>

      <!-- Right: Details -->
      <div class="col-md-7">
        <p><strong>Mô tả:</strong> {{ product.moTaGiay || 'Không có mô tả' }}</p>
        <p><strong>Thương hiệu:</strong> {{ product.thuongHieu }}</p>
        <p><strong>Chất liệu:</strong> {{ product.chatLieu }}</p>
        <p><strong>Xuất xứ:</strong> {{ product.xuatXu }}</p>
        <p><strong>Kiểu dáng:</strong> {{ product.kieuDang }}</p>
        <p><strong>Từ khóa:</strong> {{ product.tuKhoa }}</p>
        <p>
          <strong>Danh mục:</strong>
          {{ product.danhMucCon?.danhMuc?.tenDanhMuc || 'Không có' }} →
          {{ product.danhMucCon?.tenDanhMucCon || 'Không có' }}
        </p>
        <p><strong>Chọn màu sắc:</strong></p>
        <select class="form-select mb-2" v-model="selectedColor">
          <option disabled value="">Chọn màu</option>
          <option v-for="color in uniqueColors" :key="color" :value="color">{{ color }}</option>
        </select>

        <p><strong>Chọn size:</strong></p>
        <select class="form-select mb-2" v-model="selectedSize" :disabled="!selectedColor">
          <option disabled value="">Chọn size</option>
          <option v-for="size in availableSizes" :key="size" :value="size">
            {{ size }}
          </option>
        </select>

        <p><strong>Số lượng:</strong></p>
        <input
            type="number"
            class="form-control mb-3"
            v-model.number="quantity"
            :min="1"
            :max="maxQuantity"
            :disabled="!variantSelected"
        />
        <button class="btn btn-primary" :disabled="!variantSelected || quantity < 1 || quantity > maxQuantity" @click="addToCart">
          Thêm vào giỏ hàng
        </button>
      </div>
    </div>

    <!-- Chi tiết sản phẩm (biến thể) -->
    <h4 class="mt-4 mb-3">Các biến thể</h4>
    <div v-if="product.chiTietGiay?.length > 0">
      <table class="table table-bordered table-hover">
        <thead class="table-light">
        <tr>
          <th>SKU</th>
          <th>Màu sắc</th>
          <th>Size</th>
          <th>Giá bán</th>
          <th>Số lượng</th>
          <th>Trạng thái</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="ct in product.chiTietGiay" :key="ct.id">
          <td>{{ ct.sku }}</td>
          <td>{{ ct.mauSac }}</td>
          <td>{{ ct.size }}</td>
          <td>{{ ct.giaBan.toLocaleString() }}₫</td>
          <td>{{ ct.soLuong }}</td>
          <td>
              <span :class="{ 'text-success': ct.trangThai, 'text-danger': !ct.trangThai }">
                {{ ct.trangThai ? 'Hiển thị' : 'Ẩn' }}
              </span>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
    <div v-else class="text-muted">Không có biến thể chi tiết.</div>
  </div>

  <div v-else class="text-center py-5">Đang tải sản phẩm...</div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'ChiTietGiay',
  data() {
    return {
      product: null,
      selectedColor: '',
      selectedSize: '',
      quantity: 1,
    }
  },
  mounted() {
    this.fetchProduct()
  },
  computed: {
    uniqueColors() {
      const colors = this.product?.chiTietGiay?.map(ct => ct.mauSac) || []
      return [...new Set(colors)]
    },
    // Sizes available for the selected color
    availableSizes() {
      if (!this.selectedColor) return []
      return [...new Set(
          this.product.chiTietGiay
              .filter(ct => ct.mauSac === this.selectedColor)
              .map(ct => ct.size)
      )]
    },
    // Get the selected variant
    selectedVariant() {
      return this.product?.chiTietGiay?.find(
          ct => ct.mauSac === this.selectedColor && ct.size === this.selectedSize
      ) || null
    },
    maxQuantity() {
      return this.selectedVariant?.soLuong || 0
    },
    variantSelected() {
      return this.selectedVariant !== null
    }
  },
  methods: {
    async fetchProduct() {
      const id = this.$route.params.id
      try {
        const response = await axios.get(`/api/client/giay/${id}`)
        this.product = response.data
      } catch (err) {
        console.error('❌ Lỗi khi tải sản phẩm:', err)
      }
    },
    async addToCart() {
      if (!this.variantSelected) return

      const idKhachHang = localStorage.getItem('idKhachHang')
      if (!idKhachHang) {
        alert('❗ Bạn chưa đăng nhập!')
        return
      }

      const payload = {
        idKhachHang: Number(idKhachHang),
        idChiTietGiay: this.selectedVariant.id,
        soLuong: this.quantity,
      }

      try {
        const response = await axios.post('/api/client/giohang/add', payload)
        console.log('🛒 Đã thêm vào giỏ hàng:', response.data)
        alert('✅ Thêm vào giỏ hàng thành công!')
      } catch (err) {
        console.error('❌ Lỗi khi thêm vào giỏ hàng:', err.response?.data || err.message)
        alert(`❌ Lỗi: ${err.response?.data || 'Không thể thêm vào giỏ hàng'}`)
      }
    },
  },
}
</script>
