<template>
  <el-menu
    default-active="all"
    class="el-menu-vertical-demo"
    @open="handleOpen"
    @close="handleClose"
    id="el-menu"
    router
  >
    <el-sub-menu>
      <template #title>
        <el-icon><location /></el-icon>
        <span>专家列表</span>
      </template>
      <el-menu-item index="all" @click="allprof">全部专家</el-menu-item>
      <el-sub-menu>
        <template #title>
          <el-icon><location /></el-icon>
          <span>分类查看</span>
        </template>
        <el-menu-item-group>
          <template #title>
            <span>专家类型</span>
          </template>
          <!-- 此处绑定index的唯一值作为路由传递的参数，toString()方法用来转为地址 -->
          <!-- 根据index的不同转到两个不同的专家类型 -->
          <el-menu-item
            v-for="(item, index) in TpyeList"
            :key="index"
            @click="selectbyType"
            :index="index.toString()"
          >
            {{ item.professorType }}</el-menu-item
          >
        </el-menu-item-group>
        <el-menu-item-group>
          <template #title>
            <span>专家科室</span>
          </template>
          <!-- 外科 -->
          <!-- index采用字符串拼接区别上面的科室信息 -->
          <el-menu-item
            v-for="(item, index) in DepartList"
            :key="index"
            :index="flag + index.toString()"
          >
            {{ item.professorDepartment }}</el-menu-item
          >
        </el-menu-item-group>
      </el-sub-menu>
    </el-sub-menu>
    <!-- 转到提交问题 -->
    <el-menu-item index="../consult">
      <el-icon><setting /></el-icon>
      <span >询问专家</span>
    </el-menu-item>
  </el-menu>
</template>

<script>
import cookies from "vue-cookies";
import request from "@/utils/request.js";
export default {
  name: "DetialsAside",
  data() {
    return {
      flag: "dep", //拼接通过科室查询的路径信息
      TpyeList: [],
      DepartList: [],
    };
  },
  mounted() {
    request.get("/professorDetails/getProType").then((res) => {
      //后端传过来类型，前端给到List数组，渲染到菜单
      // console.log(res.data)
      for (let i = 0; i < res.data.length; i++) {
        this.TpyeList.push(res?.data[i]);
      }
      console.log("专家类型");
      console.log(this.TpyeList);
    });

    request.get("/professorDetails/getProDepart").then((res) => {
      // 专家科室
      for (let i = 0; i < res.data.length; i++) {
        this.DepartList.push(res?.data[i]);
      }
    });
  },
  methods: {
    
    allprof() {
      this.$router.push("/professorDetails/all");
    },
    selectbyType() {},
  },
};
</script>

<style scoped>
#el-menu {
  height: 50vh;
}
</style>
