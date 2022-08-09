<template>
  <div class="home">
    <div style="width: 100%; height: 100px; text-align:center;font-size:30px;">
      <span style="magin-top:50px;margin-right:100px">购物车管理</span>
    </div>
    <!-- 表格 -->
    <div>
      <el-table :data="tableData" stripe>
        <el-table-column prop="id" label="ID" />
        <el-table-column prop="medicineId" label="药品id" />

        <el-table-column prop="medicineNum" label="药品数量" />
        <el-table-column prop="userId" label="用户id" />

        <!-- 操作 -->
      </el-table>

      <div class="demo-pagination-block" style="margin: 10px 0">
        <el-pagination
          v-model:currentPage="currentPage4"
          :page-sizes="[5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        >
        </el-pagination>
        <!-- <el-button type = "primary" @click="jump">go</el-button> -->
      </div>
    </div>
    <!-- 弹窗 -->
  </div>
</template>

<script>
import Table from "@/components/Table.vue";
import request from "@/utils/request.js";
export default {
  name: "",
  components: {
    Table,
  },
  data() {
    return {
      tableData: [],
      currentPage4: 1,
      pageSize: 5,
      total: 22,
    };
  },
  created() {
    this.load();
  },
  methods: {
    load() {
      request
        .get("/cart/getAll", {
          params: {
            pageNum: this.currentPage4,
            pageSize: this.pageSize,
          },
        })
        .then((result) => {
          console.log(result);
          this.tableData = result.data.records;
          this.total = result.data.total;
        });
    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize;
      this.load();
    },
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum;
      this.load();
    },
  },
};
</script>

<style scoped>
.home {
  padding: 10px;
  /*float: right;*/
  width: calc(100% - 200px);
}
</style>
