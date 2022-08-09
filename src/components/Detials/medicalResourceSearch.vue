<template>
  <div class="search">
    <el-input
      style="margin: 10px 10px; width: 20%"
      v-model="Search"
      placeholder="请输入查询内容"
      size="large"
    >
    </el-input>

    <el-button
      size="large"
      type="primary"
      @click="search"
      style="margin: 10px 10px;width:120px"
      round
    >
      <el-icon><search /></el-icon>查询</el-button
    >
  </div>
</template>

<script>
import vueEvent from "../../utils/bus";
import request from "@/utils/request.js";
export default {
  name: "professorSearch",
  data() {
    return {
      Search: "",
    };
  },
  methods: {
    search() {
      request
        .get("/medicineResource/selectBySearch", {
          params: {
            Search: this.Search,
          },
        })
        .then((res) => {
          console.log(res)
          //总线传值
          vueEvent.emit("data", res);
        });
      this.$router.push("/medicalResource/searchRes");
    },
  },
};
</script>

<style scoped>
.search {
  width: 100%;
  display: flex;
  justify-content: center;
}
</style>
