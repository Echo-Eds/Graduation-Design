<template>
  <!-- 我的个人信息 -->
  <div class="info">
    <div class="info_title">
      <span>UserCenter</span>
      <br />
      <span>个人中心</span>
    </div>
    <br />
    <!-- <el-skeleton style="width: 240px">
      <template #template>
        <el-skeleton-item variant="image" style="width: 240px; height: 240px" />
        
      </template>
    </el-skeleton> -->
    <div class="info_form">
      <!-- <div class="user_icon">
        <el-form :model="imgform">
          <el-form-item>
            <el-dialog v-model="imgformdialogVisible">
              <span :v-model="imgform.id"></span>
            </el-dialog>
          </el-form-item>
        </el-form>
      </div> -->

      <br /><br />
      <el-form
        :model="form"
        :label-position="right"
        label-width="auto"
        style="width: 640px"
        size="large"
      >
        <el-form-item label="ID">
          <el-input prefix-icon="" v-model="form.id" disabled></el-input>
        </el-form-item>
        <el-form-item label="用户名">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="form.password"></el-input>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input v-model="form.age" disabled placeholder="年龄不可修改！！"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-input v-model="form.sex" disabled placeholder="性别不可修改！！！"></el-input>
        </el-form-item>
        <el-form-item label="科室">
          <el-input v-model="form.department" disabled placeholder="不可修改"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button
            type="primary"
            round
            plain
            style="width: 100%; text-align: center"
            @click="alterMyInfo"
            >修改信息</el-button
          >
        </el-form-item>
      </el-form>
    </div>
  </div>
  <!-- 弹出确认框 -->
  <el-dialog
    v-model="dialogVisible"
    title="Tips"
    width="30%"
    :before-close="handleClose"
  >
    <span>您还未登录！</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="backToIndex">Cancel</el-button>
        <el-button type="primary" @click="goToLogin">Confirm</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script>
import request from "@/utils/request.js";
import cookies from "vue-cookies";
export default {
  name: "ProfessorInfoCenter",
  data() {
    return {
      dialogVisible: false,
      imgformdialogVisible: false,
      form: [],
      imgform: [],
      // reloadForm: [],
      // imageUrl: "",
      // user_icon: "",
    };
  },
  mounted() {
    this.load();
  },
  methods: {
    load() {
      let str = this.$cookies.get("professor")
      //如果存在用户信息就去登录 如果不存在就去
      if (str) {
        this.form = str;
        console.log(this.form);
        // this.imageUrl = this.form.userIcon;
      } else {
        this.dialogVisible = true;
      }

    },

    alterMyInfo() {
      request.put("/prfInfo/updateProfInfo", this.form).then((result) => {
        console.log(result);
        if (result.code == "0") {
          this.$message({
            type: "success",
            message: "修改成功",
          });
        } else {
          this.$message({
            type: "error",
            message: res.msg,
          });
        }
        
        this.$cookies.remove("professor")
        this.$cookies.set("professor", JSON.stringify(result.data))
        console.log(this.$cookies.get("professor"))
      });
    },
    // uploadToDB() {
    //   console.log(this.imgform);
    //   request.post("/myinfo/insert", this.imgform).then((result) => {
    //     console.log(result);
    //   });
    // },
    goToLogin() {
      this.$router.push("/userLogin");
    },
    backToIndex() {
      this.$router.push("/");
    },
    fileUploadSuccess(res) {
      console.log(res);
      console.log("-------");
      console.log(this.imageUrl);
      this.form.userIcon = res.data;
      this.imageUrl = res.data;
      console.log("-------");
      console.log(this.imageUrl);
      console.log(this.form);
    },
  },
};
</script>

<style scoped>
.info {
  width: 700px;
  margin: 100px auto 50px;
  text-align: center;
}
.info span {
  display: flex;
  justify-content: center;
  font-size: 24px;
  font-weight: 400;
}
.user_icon {
  width: 10%;
  height: 70px;
  margin: 10px 45%;
}
.user_icon img {
  max-width: 70px;
  max-height: 70px;
}
</style>
