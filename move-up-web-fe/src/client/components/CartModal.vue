<template>
  <button @click="openCartModal" class="nav-link bg-transparent border-0">
    <font-awesome-icon icon="cart-shopping"/>
  </button>

  <!-- Cart Modal -->
  <b-modal v-model="showCartModal" title="Giỏ hàng của bạn" hide-footer size="lg">
    <div v-if="loadingCart" class="text-center py-4">
      <b-spinner small/>
      Đang tải...
    </div>
    <div v-else>
      <div v-if="cartItems.length === 0">Giỏ hàng trống.</div>
      <div v-else>
        <table class="table table-bordered align-middle">
          <thead class="table-light">
          <tr>
            <th>Sản phẩm</th>
            <th>Đơn giá</th>
            <th>Số lượng</th>
            <th>Thành tiền</th>
            <th></th>
          </tr>
          </thead>
          <tbody>
            <tr v-for="item in cartItems" :key="item.id">
              <td>{{ item.chiTietGiay?.giay?.tenGiay }}</td>
              <td>{{ formatCurrency(item.chiTietGiay?.giaBan) }}</td>
              <td>{{ item.soLuong }}</td>
              <td>{{ formatCurrency(item.thanhTien) }}</td>
              <td>
                <button class="btn btn-sm btn-danger" @click="removeItem(item.id)">
                  <font-awesome-icon icon="trash"/>
                </button>
              </td>
            </tr>
          </tbody>
        </table>

        <!-- Footer section -->
        <div class="d-flex justify-content-between align-items-center mt-3 border-top pt-3">
          <div><strong>Tổng cộng:</strong> {{ formatCurrency(totalPrice) }}</div>
          <button class="btn btn-success">
            <font-awesome-icon icon="credit-card" class="me-1"/>
            Mua ngay
          </button>
        </div>
      </div>
    </div>
  </b-modal>
</template>

<script>
import axios from "axios";
import {BModal, BSpinner} from "bootstrap-vue-next";
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";

export default {
  components: {BModal, BSpinner, FontAwesomeIcon},
  data() {
    return {
      showCartModal: false,
      cartItems: [],
      loadingCart: false,
    };
  },
  computed: {
    totalPrice() {
      return this.cartItems.reduce((sum, item) => sum + item.thanhTien, 0);
    },
  },
  methods: {
    async openCartModal() {
      this.showCartModal = true;
      this.loadingCart = true;
      try {
        const idKhachHang = localStorage.getItem("idKhachHang");
        const response = await axios.get(`/api/client/giohang/${idKhachHang}`);
        console.log(response.data.chiTietGioHangList);
        this.cartItems = response.data.chiTietGioHangList || [];
      } catch (error) {
        console.error("Lỗi khi tải giỏ hàng:", error);
        this.cartItems = [];
      } finally {
        this.loadingCart = false;
      }
    },
    formatCurrency(value) {
      return value?.toLocaleString("vi-VN", {
        style: "currency",
        currency: "VND",
      });
    },
    removeItem(id) {
      // You can later implement API call here
      this.cartItems = this.cartItems.filter(item => item.id !== id);
    },
  },
};
</script>
