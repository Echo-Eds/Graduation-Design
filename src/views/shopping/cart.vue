<template>
  <SysHeader></SysHeader>
  <IndexCarousel></IndexCarousel>
  <el-row>
    <div class="info_title">
      <span
        ><el-icon><ShoppingCartFull /></el-icon>&nbsp;Cart</span
      >
      <br />
      <span>购物车</span>
    </div>
  </el-row>
  <el-row>
    <el-col :span="4"></el-col>
    <el-col :span="16">
      <el-table
        ref="multipleTableRef"
        :data="tableData"
        style="width: 100%"
        @selection-change="handleSelectionChange"
        :row-style="{ height: '70px' }"
        border
      >
        <el-table-column type="selection" width="55" />
        <el-table-column label="">
          <template #default="scope">
            <el-image
              :src="scope.row.iconPath"
              fit="contain"
              style="width: 100px; height: 100px"
              :preview-src-list="[scope.row.iconPath]"
            ></el-image>
          </template>
        </el-table-column>
        <el-table-column label="药品名称" prop="name"></el-table-column>
        <el-table-column label="价格" prop="price">
          <template #default="scope">
            <span class="price">￥：{{ scope.row.price }}</span>
          </template>
        </el-table-column>
        <el-table-column label="购买数量" align="center">
          <template #default="scope">
            <el-input-number
              v-model="scope.row.medicineNum"
              :min="1"
              :max="99"
              @change="changeNum(scope.row.medicineId, scope.row.medicineNum)"
            ></el-input-number>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="170px">
          <template #default="scope">
            <el-popconfirm
              title="确定从购物车删除？"
              @confirm="handleDelete(scope.row.medicineId)"
            >
              <template #reference>
                <el-button color="#eebe77">删除药品</el-button></template
              >
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
      <el-divider></el-divider>

      <div style="text-align: right">
        <div style="padding: 10px 0">
          当前已选商品总价： <span class="price">￥：{{ totalPrice }}</span>
        </div>
        <el-button
          type="success"
          size="large"
          style="width: 200px"
          round
          @click="upOrders"
          :disabled="disabled"
          >提交订单</el-button
        >
      </div>
    </el-col>
    <el-col :span="4"></el-col>
  </el-row>
  <div style="width: 100%; height: 150px"></div>
  <SysBottom></SysBottom>
</template>

<script>
import SysHeader from "../../components/sys/SysHeader.vue";
import IndexCarousel from "../../components/sys/IndexCarousel.vue";
import SysBottom from "../../components/sys/SysBottom.vue";

import request from "@/utils/request.js";
export default {
  name: "cart",
  components: {
    SysHeader,
    IndexCarousel,
    SysBottom,
  },
  data() {
    return {
      tableData: [],
      multipleSelection: [],
      totalPrice: 0,
      str: JSON.parse(sessionStorage.getItem("user")),
      ordersNo: 0,
      disabled: true,
    };
  },
  mounted() {
    this.load();
  },
  methods: {
    load() {
      this.tableData = [];

      request.get("/cart/selectMyCart/" + this.str.id).then((res) => {
        console.log(res);
        let medicineNUM = res.data.length;
        //   this.CardList.length = professorNUM;

        for (let flag = 0; flag < medicineNUM; flag++) {
          this.tableData.push(res?.data[flag]);
        }
        console.log(this.tableData);
      });
    },
    handleSelectionChange(val) {
      console.log(val);
      this.totalPrice = 0;
      this.multipleSelection = val;
      //计算总价
      if (val && val.length) {
        val.forEach((item) => {
          this.totalPrice += item.medicineNum * item.price;
        });
      }
      this.totalPrice = this.totalPrice.toFixed(2);
      if (this.totalPrice > 0) {
        this.disabled = false;
      }
      if (this.totalPrice == 0) {
        this.disabled = true;
      }
    },
    changeNum(id, num) {
      console.log(id);
      console.log(num);
      console.log(this.str.id);
      request
        .post("/cart/updateNum/" + num + "/" + id + "/" + this.str.id)
        .then((res) => {
          console.log(res);
        });
      this.totalPrice = 0;
      this.multipleSelection.forEach((item) => {
        this.totalPrice += item.medicineNum * item.price;
      });
      this.totalPrice = this.totalPrice.toFixed(2);
      console.log(this.totalPrice);
    },
    upOrders() {
      //创建订单
      request
        .post("/cart/addOrder/" + this.str.id + "/" + this.totalPrice)
        .then((res) => {
          // console.log(res);
          this.ordersNo = res.data;
          //获取订单中的商品信息
          this.multipleSelection.forEach((item) => {
            let medicineId = item.medicineId;
            let medicineNum = item.medicineNum;
            request
              .post(
                "/cart/addOrderMedicine/" +
                  this.ordersNo +
                  "/" +
                  medicineId +
                  "/" +
                  medicineNum
              )
              .then((res) => {
                if (res.code == "0") {
                  this.$router.push({
                    path: "/order",
                    query: {
                      orderNo: this.ordersNo,
                    },
                  });
                } else {
                  this.$message({
                    type: "error",
                    message: res.msg,
                    showClose: true,
                    center: true,
                  });
                }
              });
          });
        });
      this.$message({
        type: "success",
        message: "提交成功！",
        showClose: true,
        center: true,
      });
    },

    handleDelete(medicineId) {
      request.delete("/cart/deleteRow/" + medicineId + "/" + this.str.id).then((res) => {
        if (res.code == "0") {
          this.$message({
            type: "success",
            message: "删除成功！",
            showClose: true,
            center: true,
          });
        } else {
          this.$message({
            type: "error",
            message: res.msg,
            showClose: true,
            center: true,
          });
        }
        this.load();
      });
    },
  },
};
</script>

<style scoped>
.info_title {
  width: 900px;
  margin: 40px auto;
  text-align: center;
}
.info_title span {
  display: flex;
  justify-content: center;
  font-size: 24px;
  font-weight: 400;
}
.myLocat {
  height: 400px;
}
.price {
  font-size: 24px;
  font-family: "Times New Roman", Times, serif;
  font-weight: 200;
  color: red;
}
.upOrder {
  height: 100px;
}
.upOrder span {
  display: inline;
  font-size: 24px;
}
.price {
  color: orangered;
}
</style>
