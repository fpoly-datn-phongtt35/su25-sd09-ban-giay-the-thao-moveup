<template>
  <div class="container">
    <h2>📦 Danh Sách Hóa Đơn</h2>

    <div v-if="loading" class="loading">Đang tải dữ liệu...</div>

    <table v-else class="invoice-table">
      <thead>
      <tr>
        <th>Thông tin hóa đơn</th>
        <th>Thông tin khách hàng</th>
        <th>Chi tiết hóa đơn</th>
        <th>Hành động</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="item in items" :key="item.id">
        <!-- Thông tin hóa đơn -->
        <td>
          ID: {{ item.maHoaDon || 'Không rõ' }}<br />
          Ngày tạo: {{ item.ngayTao }}
        </td>

        <!-- Thông tin khách hàng -->
        <td>
          <div v-if="item.khachHang">
            Tên: {{ item.khachHang.hoTen }}<br />
            SĐT: {{ item.khachHang.soDienThoai }}
          </div>
          <div v-else>
            Tên: {{ item.tenKhachHang || 'N/A' }}<br />
            SĐT: {{ item.soDienThoaiKhachHang || 'N/A' }}
          </div>
        </td>

        <!-- Chi tiết hóa đơn -->
        <td>
          {{ item.chiTietHoaDon?.id }}
        </td>

        <!-- Hành động -->
        <td>
          <!-- Placeholder for buttons -->
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      items: [],
      loading: false,
    };
  },
  methods: {
    async load() {
      this.loading = true;
      try {
        const res = await axios.get('/hoa-don');
        console.log("✅ Response:", res.data);
        this.items = res.data.content; // ✅ Fix here
      } catch (error) {
        console.error("❌ Error loading:", error);
        alert(error.message);
      } finally {
        this.loading = false;
      }
    }
  },
  mounted() {
    this.load();
  }
};
</script>

<style scoped>
.container {
  max-width: 960px;
  margin: 2rem auto;
}

.loading {
  font-style: italic;
  margin-bottom: 1rem;
  color: #888;
}

.invoice-table {
  width: 100%;
  border-collapse: collapse;
}

.invoice-table th,
.invoice-table td {
  border: 1px solid #ccc;
  padding: 8px;
  vertical-align: top;
}

.invoice-table th {
  background-color: #f5f5f5;
  text-align: left;
}
</style>
