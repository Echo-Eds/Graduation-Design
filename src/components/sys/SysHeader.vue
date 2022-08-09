<template>
  <el-affix z-index="100">
    <div class="header">
      <div class="header-a">医药知识信息平台</div>

      <div style="flex: 1">
        <el-menu
          :default-active="activeIndex"
          mode="horizontal"
          text-color=" #66b1ff"
          @select="handleSelect"
        >
          <el-menu-item index="1" @click="backToHome">首页</el-menu-item>
          <el-sub-menu index="2">
            <template #title>医师</template>
            <el-menu-item index="2-1" @click="goToProfessorDetails"
              >医师信息</el-menu-item
            >
            <el-menu-item index="2-2" @click="goToConsult">医师解答</el-menu-item>
          </el-sub-menu>
          <el-menu-item index="2-3" @click="goToSkillsTraining">技能培训</el-menu-item>
          <el-sub-menu index="3">
            <template #title>医药资源</template>
            <el-menu-item @click="goToMedicineResource">医药资源</el-menu-item>
            <el-menu-item @click="goToMedicalInformation">医药资讯</el-menu-item>
          </el-sub-menu>
          <el-menu-item index="4" @click="goToMedicineInfo">药品信息</el-menu-item>
          <el-menu-item index="5" @click="cart">购物车</el-menu-item>
          <el-menu-item index="6" @click="goToMyInfo">个人中心</el-menu-item>
        </el-menu>
      </div>

      <div class="header-b">
        <el-dropdown>
          <span class="el-dropdown-link">
            {{ nickName }}
            <el-icon class="el-icon--right">
              <arrow-down />
            </el-icon>
          </span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="goToMyInfo">个人信息</el-dropdown-item>
              <el-dropdown-item @click="logout">退出系统</el-dropdown-item>
              <el-dropdown-item @click="Mag">后台</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </div>
  </el-affix>
</template>

<script>
import { ArrowDown } from "@element-plus/icons-vue";
import cookies from "vue-cookies";
export default {
  name: "SysHeader",
  components: {
    ArrowDown,
  },
  data() {
    return {
      nickName: "请登录",
    };
  },
  created() {
    this.load();
  },
  methods: {
    load() {
      // console.log("setcookies")
      // this.$cookies.set("professor",JSON.stringify(localStorage.getItem("professor")))

      //user和professor不能同时登录 所以if else
      let str = this.$cookies.get("professor");
      console.log("professor");
      console.log(str);
      if (str) {
        this.nickName = str.name;
      } else {
        let str1 = JSON.parse(sessionStorage.getItem("user" || "{}"));
        console.log("user");
        console.log(str1);

        if (str1) {
          this.nickName = str1.nickName;
        }
      }
    },
    logout() {
      //清除User数据back到Login
      sessionStorage.removeItem("user");
      this.$cookies.remove("professor");

      this.$router.push("/userLogin");

      this.$message({
        type: "success",
        message: "logout success! please ReLogin",
      });
    },
    backToHome() {
      this.$router.push("/");
    },
    Mag() {
      this.$router.push("/Mag/userManage");
    },
    goToProfessorDetails() {
      this.$router.push("/professorDetails");
    },
    goToMyInfo() {
      let str = sessionStorage.getItem("user" || "{}");
      let str1 = this.$cookies.get("professor");
      console.log("-------------");
      console.log(str);
      console.log(str1);
      if (str == null && str1 == null) {
        this.$router.push("/myInfo");
      }
      if (str == null && str1 != null) {
        console.log("gotoprofessordetails");
        this.$router.push("/profInfo");
      }
      if (str != null && str1 == null) {
        console.log("gotouser");
        this.$router.push("/myInfo");
      }
    },
    goToConsult() {
      this.$router.push("/consult");
    },
    goToMedicineInfo() {
      this.$router.push("/MedicineInfo");
    },
    goToSkillsTraining() {
      this.$router.push("/skills_training");
    },
    goToMedicalInformation() {
      this.$router.push("/medicalInformation");
    },
    goToMedicineResource() {
      this.$router.push("/medicalResource");
    },
    cart() {
      let str = sessionStorage.getItem("user" || "{}");
      let str1 = this.$cookies.get("professor");
      if (str1) {
        this.$message({
          type: "warning",
          message: "专家用户无购物车！",
          showClose: true,
          center: true,
        });
      } 
      if(str == null && str1 == null) {
        this.$message({
          type: "warning",
          message: "请登录",
          showClose: true,
          center: true,
        });
      }
      else {
        this.$router.push("/cart");
      }
    },
  },
};
</script>

<style scoped>
.el-menu {
  --el-menu-border-color: none;
  /* --el-menu-bg-color:#955a5a; */
}
.el-menu-item {
  line-height: inherit;
}
.header {
  width: 100%;
  /* height: 70px; */
  background-color: #ffffff;
  line-height: 70px;
  border: 1px solid #cccccc;
  display: flex;
}

.header-a {
  width: 35%;
  padding-left: 40px;
  font-weight: bolder;
  font-size: 24px;
  color: #409eff;
}

.header-b {
  width: 10%;
  /* font-size: 16px; */
}

.el-dropdown-link {
  cursor: pointer;
  color: var(--el-color-primary);
  display: flex;
  align-items: center;
  line-height: 70px;
}
</style>
