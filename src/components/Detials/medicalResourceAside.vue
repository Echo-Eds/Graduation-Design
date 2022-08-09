<template>
  <el-menu
    default-active="main"
    class="el-menu-vertical-demo"
    @open="handleOpen"
    @close="handleClose"
    id="el-menu"
    router
  >
    <el-sub-menu>
      <template #title>
        <el-icon><location /></el-icon>
        <span>医药资源</span>
      </template>
      <el-menu-item index="main" @click="main">全部资源</el-menu-item>
      <el-sub-menu>
        <template #title>
          <el-icon><location /></el-icon>
          <span>分类查看</span>
        </template>
        <el-menu-item-group>
          <template #title>
            <span>资源类型</span>
          </template>
          <!-- 此处绑定index的唯一值作为路由传递的参数，toString()方法用来转为地址 -->
          <!-- 根据index的不同转到两个不同的专家类型 -->
          <el-menu-item
            v-for="(item, index) in TpyeList"
            :key="index"
            @click="selectbyType(index, item)"
            :index="index.toString()"
          >
            {{ item.type }}</el-menu-item
          >
        </el-menu-item-group>
      </el-sub-menu>
    </el-sub-menu>
  </el-menu>
</template>

<script>
import request from "@/utils/request.js";

export default {
  name: "",
  data() {
    return {
      flag: "dep", //拼接通过科室查询的路径信息
      TpyeList: [],
    };
  },
  mounted() {
    request.get("/medicineResource/getType").then((res) => {
      //后端传过来类型，前端给到List数组，渲染到菜单
      console.log(res);
      for (let i = 0; i < res.data.length; i++) {
        this.TpyeList.push(res?.data[i]);
      }
      console.log("培训类型");
      console.log(this.TpyeList);
    });
  },
  methods: {
    selectbyType(index, item) {
      this.$router.push({
        path: "/medicalResource/" + index,
        query: {
          type: item.type,
        },
      });
    },
  },
};
</script>

<style scoped>
#el-menu {
  height: 50vh;
}
</style>
