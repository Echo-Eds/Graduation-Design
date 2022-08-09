<template >
  <SysHeader></SysHeader>
  <IndexCarousel></IndexCarousel>
  <ProfessorSearch></ProfessorSearch>
  <el-row>
    <el-col :span="4"></el-col>
    <el-col :span="4">
      <DetialsAside></DetialsAside>
    </el-col>
    <el-col :span="16">
      <router-view></router-view>
    </el-col>
  </el-row>
  <br /><br />
  <SysBottom></SysBottom>
</template>

<script>

import SysHeader from "../components/sys/SysHeader.vue";
import IndexCarousel from "../components/sys/IndexCarousel.vue";
import DetialsAside from "../components/Detials/professprDetialsAside.vue";
import SysBottom from "../components/sys/SysBottom.vue";
import ProfessorSearch from "../components/Detials/professorSearch.vue"
export default {
  name: "ProfessorDetailsLayout",
  components: {
    
    SysHeader,
    IndexCarousel,
    DetialsAside,
    SysBottom,
    ProfessorSearch,
  },
  data() {
    return {};
  },
};
</script>

<style scoped>
</style>
