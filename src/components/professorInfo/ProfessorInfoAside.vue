<template>
<el-row>
  <el-col :span="24"><el-menu
      id="el-menu"
      active-text-color="#ffd04b"
      background-color="#545c64"
      class="el-menu-vertical-demo"
      default-active="myInfoCenter"
      text-color="#fff"
      @open="handleOpen"
      @close="handleClose"
      router
    >
      <el-sub-menu index="1">
        <template #title>
          <el-icon><user-filled /></el-icon>
          <span>个人中心</span>
        </template>

        <el-menu-item index="profInfoCenter">我的信息</el-menu-item>
        
      </el-sub-menu>
       
      <el-menu-item index="profInfoLssue">
        <el-icon><collection-tag /></el-icon>
        <span>我的发布</span>
      </el-menu-item>
      <el-menu-item index="releaseInfo">
        <span>发布资讯</span>
      </el-menu-item>
    </el-menu></el-col>
</el-row>
  
</template>

<script>
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from "@element-plus/icons-vue";
export default {
  name: "ProfessorInfoAside",
  components: {
    Location,
    Document,
    Setting,
    IconMenu,
  },
  data() {
    return {};
  },
};
</script>

<style scoped>
#el-menu {
  width: 100%;
  height: calc(100vh - 74px);
}
</style>
