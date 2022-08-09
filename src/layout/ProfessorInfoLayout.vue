<template>
  <SysHeader></SysHeader>
  
    <el-row>
      <el-col :span="3">
        <ProfessorInfoAside></ProfessorInfoAside>
        </el-col>
      <el-col :span="21"><router-view></router-view></el-col>
    </el-row>
  
</template>

<script>
import SysHeader from "../components/sys/SysHeader.vue";
import ProfessorInfoAside from "../components/professorInfo/ProfessorInfoAside.vue";
export default {
  name: "MyInfoLayout",
  components: {
    SysHeader,
    ProfessorInfoAside,
  },
  data() {
    return {};
  },
};
</script>

<style scoped>
</style>
