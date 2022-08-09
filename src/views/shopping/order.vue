<template>
  <SysHeader></SysHeader>
  <IndexCarousel></IndexCarousel>
  <br />
  <el-row>
    <el-col :span="7"></el-col>
    <el-col :span="12">
      <el-page-header content="订单：未支付" @click="goBack" />
    </el-col>
    <el-col :span="4"></el-col>
  </el-row>

  <el-row>
    <el-col :span="8"></el-col>
    <el-col :span="8">
      <el-divider></el-divider>
      <div class="orderDetails">
        <span>请选择您的收货地址：</span>
        <div style="width: 100%; height: 8px"></div>
        <el-select
          v-model="address"
          placeholder="请选择"
          @change="handleAddressChane"
          style="width: 415px"
        >
          <el-option v-for="item in addressList" :key="item" :value="item">{{
            item
          }}</el-option>
        </el-select>
      </div>
      <el-divider></el-divider>
      <el-card>
        <div class="orderDetails">
          <span>订单号：{{ orderNo }} </span>
          <!-- <br />
          <span>金额： {{ totalPrice }} </span> -->
          <br />
          <span>支付时间： {{ orderTime }} </span>
          <br />
        </div>
      </el-card>
      <el-divider></el-divider>

      <el-card v-for="item in medicineList" :key="item">
        <el-row>
          <el-col :span="8">
            <el-image style="width: 100px; height: 100px" :src="item.iconPath"></el-image>
          </el-col>
          <el-col :span="2"></el-col>
          <el-col :span="12">
            <div class="medInfo">
              <br />
              <span>{{ item.name }}</span>
              <br />
              <span>￥：{{ item.price }} x {{ item.medicineNum }}</span>
              <br />
              <span>{{ item.type }}</span>
            </div>
          </el-col>
        </el-row>
      </el-card>

      <el-divider></el-divider>

      <div style="text-align: right">
        <span style="font-size: 20px">总价￥：</span>
        <span style="color: orangered; font-size: 24px"> {{ totalPrice }}</span>
      </div>
      <div style="width: 100%; height: 12px"></div>
      <div style="text-align: right">
        <el-button style="width: 120px" type="danger" round @click="openDialog"
          >去支付</el-button
        >
      </div>
    </el-col>
    <el-col :span="8"></el-col>
  </el-row>
  <div style="width: 100%; height: 200px"></div>

  <el-dialog v-model="dialogVisible">
    <el-image style="max-width: 300px" :src="alipay" ></el-image>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="payOrders">支付完成</el-button>
      </span>
    </template>
  </el-dialog>
  <SysBottom></SysBottom>
</template>

<script>
import SysHeader from "../../components/sys/SysHeader.vue";
import IndexCarousel from "../../components/sys/IndexCarousel.vue";
import SysBottom from "../../components/sys/SysBottom.vue";
import request from "@/utils/request.js";

export default {
  name: "",
  components: {
    SysHeader,
    IndexCarousel,
    SysBottom,
  },
  data() {
    return {
      user: JSON.parse(sessionStorage.getItem("user")),
      orderNo: this.$route.query.orderNo,
      totalPrice: 0,
      orderTime: "2022-05-04",
      addressList: [],
      address: "",
      medicineList: [],
      dialogVisible: false,
      alipay: "http://localhost:9090/files/alipay.jpg",
    };
  },
  mounted() {
    this.getMyAddress();
    this.load();
  },
  methods: {
    getMyAddress() {
      request.get("/address/addressToOrder/" + this.user.id).then((res) => {
        // console.log(res);
        this.addressList = res.data.reduce((Arr, next) => {
          return [...Arr, next.address];
        }, []);
        console.log(this.addressList);
      });
    },
    load() {
      console.log(this.orderNo);
      request.get("/cart/getOrderMedicineList/" + this.orderNo).then((res) => {
        this.medicineList = res.data.reduce((Arr, next) => {
          return [...Arr, next];
        }, []);
        console.log(this.medicineList);
      });
      //获取当前订单总价和支付状态
      request.get("/cart/getOrderStatus/" + this.orderNo).then((res) => {
        console.log(res);
        this.totalPrice = res.data.totalPrice;
        this.orderTime = res.data.time;
      });
    },
    // handleAddressChane() {
    //   console.log(this.address);
    // },
    openDialog() {
      this.dialogVisible = true;
    },
    payOrders() {
      request.get("/cart/updateStatus/" + this.orderNo, {
        params: {
          address: this.address
        }
      }).then((res) => {
        console.log(res);
        this.$router.push({
          path: '/orderPayed',
          query: {
            orderNo: this.orderNo
          }
        })
      })
    },
    goBack() {
      this.$router.back()
    }
  },
};
</script>

<style scoped>
.orderDetails {
  /* text-align: center; */
}
.orderDetails span {
  display: flex;
}
.medInfo {
}
.medInfo span {
  display: flex;
}
</style>
