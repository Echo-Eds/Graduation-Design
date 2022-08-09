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

        <el-menu-item index="myInfoCenter">我的信息</el-menu-item>
        <el-menu-item index="myInfoLssue">我的发布</el-menu-item>
      </el-sub-menu>
      <el-menu-item index="myOrder">
        <el-icon><icon-menu /></el-icon>
        <span>我的订单</span>
      </el-menu-item>

      <el-menu-item index="myLocation">
        <el-icon><location /></el-icon>
        <span>我的地址</span>
      </el-menu-item>
      <el-menu-item index="myCollect">
        <el-icon><collection-tag /></el-icon>
        <span>我的收藏</span>
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
  name: "MyInfoAside",
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
