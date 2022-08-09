<template>
  <SysHeader></SysHeader>
  
    <el-row>
      <el-col :span="3">
        <MyInfoAside></MyInfoAside></el-col>
      <el-col :span="21"><router-view></router-view></el-col>
    </el-row>
  
</template>

<script>
import SysHeader from "../components/sys/SysHeader.vue";
import MyInfoAside from "../components/myInfo/MyInfoAside.vue";
export default {
  name: "MyInfoLayout",
  components: {
    SysHeader,
    MyInfoAside,
  },
  data() {
    return {};
  },
};
</script>

<style scoped>
</style>
