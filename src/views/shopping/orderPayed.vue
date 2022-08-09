<template>
  <SysHeader></SysHeader>
  <IndexCarousel></IndexCarousel>
  <br />
  <el-row>
    <el-col :span="7"></el-col>
    <el-col :span="12">
      <el-page-header :content="orderPayStatus" @click="goBack" />
    </el-col>
    <el-col :span="4"></el-col>
  </el-row>

  <el-row>
    <el-col :span="8"></el-col>
    <el-col :span="8">
      <el-divider></el-divider>
      <div class="orderDetails">
        <span>收货地址：{{ address }}</span>
      </div>
      <el-divider></el-divider>
      <el-card>
        <div class="orderDetails">
          <span>订单号：{{ orderNo }} </span>
          <!-- <br />
          <span>金额： {{ totalPrice }} </span> -->
          <br />
          <span>下单时间： {{ orderTime }} </span>
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
    </el-col>
    <el-col :span="8"></el-col>
  </el-row>
  <div style="width: 100%; height: 200px"></div>
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
      orderPayStatus: "",
    };
  },
  mounted() {
    this.load();
  },
  methods: {  
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
        this.orderPayStatus = res.data.state;
        this.address = res.data.address
      });
    },
    // handleAddressChane() {
    //   console.log(this.address);
    // },
    openDialog() {
      this.dialogVisible = true;
    },
    goBack() {
      this.$router.push("/myinfo/myOrder")
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
