<template>
  <div class="header">
    <div class="header-a">后台管理</div>
    <div style="flex: 1"></div>
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
            <el-dropdown-item @click="$router.push('/Mag/adminInfo')"
              >个人信息</el-dropdown-item
            >
            <el-dropdown-item @click="logout">退出系统</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>

<style scoped>
.header {
  height: 50px;
  line-height: 50px;
  border: 1px solid #cccccc;
  display: flex;
}

.header-a {
  width: 200px;
  padding-left: 30px;
  font-weight: bolder;
}

.header-b {
  width: 100px;
}

.el-dropdown-link {
  cursor: pointer;
  color: var(--el-color-primary);
  display: flex;
  align-items: center;
  line-height: 50px;
}
</style>
<script>
import { ArrowDown } from "@element-plus/icons-vue";

export default {
  name: "Header",
  components: {
    ArrowDown,
  },
  data() {
    return {
      nickName: JSON.parse(sessionStorage.getItem("admin") || "{}")
        .adminNickname,
    };
  },
  methods: {
    logout() {
      //清除User数据back到Login
      this.$router.push("/adminLogin");
      sessionStorage.removeItem("admin");
      this.$message({
        type: "success",
        message: "logout success! please ReLogin",
      });
    },
  },
};
</script>