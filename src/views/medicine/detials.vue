<template>
  <div class="details">
    <el-card>
      <el-row>
        <div class="pageHeader">
          <el-page-header content="详情：" @click="goBack" />
        </div>
      </el-row>
      <el-divider></el-divider>
      <el-row>
        <el-col :span="12">
          <div><img :src="medicineInfo.profIcon" alt="" /></div>
        </el-col>
        <el-col :span="12">
          <div class="info1">
            <span>药品名称：</span>
            <span>{{ medicineInfo.name }}</span>
          </div>
          <div class="info1">
            <span>药品类型：</span>
            <span>{{ medicineInfo.type }}</span>
          </div>
          <div class="info1">
            <span>库存：</span>
            <span>{{ medicineInfo.stock }}</span>
          </div>
          <div class="info1">
            <span>价格：</span>
            <span>{{ medicineInfo.price }}</span>
          </div>
          <div class="info1">
            <el-button
              type="primary"
              round
              size="large"
              @click="addCart(medicineInfo.id, medicineInfo.name)"
              ><el-icon><ShoppingCart /></el-icon>&nbsp;加入购物车</el-button
            >
          </div>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
import request from "@/utils/request.js";
export default {
  name: "",
  data() {
    return {
      id: "",
      medicineInfo: {},
    };
  },
  mounted() {
    this.id = this.$route.query.medicineId;
    console.log("此页面ID");
    console.log(this.id);
    this.load();
  },
  methods: {
    load() {
      request.get("/medicine/getDetails/" + this.id).then((res) => {
        console.log(res);
        this.medicineInfo.id = res.data.id;
        this.medicineInfo.profIcon = res.data.iconPath;
        this.medicineInfo.name = res.data.name;
        this.medicineInfo.type = res.data.type;
        this.medicineInfo.stock = res.data.stock;
        this.medicineInfo.price = res.data.price;
      });
    },
    addCart(id,name) {
        let str = JSON.parse(sessionStorage.getItem("user")) 
        let str1 = this.$cookies.get("professor")
        if(str1) {
          this.$message({
            type: "warning",
            message: "只有普通用户才能购买药品",
            showClose: true,
            center: true,
          })
        }
        if(str) {
          let form = {}
          form.medicineId = id;
          form.medicineName = name;
          form.userId = str.id;
          request.post("/cart/addOne", form).then((res) => {
            console.log(res);
            if(res.code == "0") {
              this.$message({
                type: 'success',
                message: '添加成功',
                showClose: true,
                center: true
              })
            } else {
              this.$message({
                type: 'error',
                message: res.msg,
                showClose: true,
                center: true
              })
            }
          })
        }
        if(!str && !str1) {
          this.$message({
            type: "warning",
            message: "请先登录再购买！",
            showClose: true,
            center: true,
          })
        }
    },
    goBack() {
      this.$router.back()
    }
  },
};
</script>

<style scoped>
.details {
  width: 800px;
  margin: 10px 100px;
}
.details img {
  width: 350px;
  height: 350px;
  border-radius: 20%;
}
.info1 {
  height: 50px;
  margin-top: 10px;
  margin-bottom: 10px;
}
.info2 {
  margin-top: 10px;
  margin-bottom: 10px;
}
</style>
