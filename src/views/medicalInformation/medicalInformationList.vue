<template>
  <SysHeader></SysHeader>
  <IndexCarousel></IndexCarousel>
  <el-row>
    <el-col>
      <div class="search">
        <el-input
          style="margin: 10px 10px; width: 20%"
          v-model="Search"
          placeholder="查询信息"
          clearable
          size="large"
        />
        <el-button
          type="primary"
          round
          size="large"
          color="#40a9ff"
          @click="search"
          style="margin: 10px 10px"
        >
          <el-icon><search /></el-icon>查询</el-button
        >
      </div>
    </el-col>
  </el-row>
  <el-divider></el-divider>
  <el-row>
    <el-col :span="6"></el-col>
    <el-col :span="12">
      <el-table
        :data="tableData"
        style="width: 100%"
        :row-style="{ height: '70px' }"
        stripe
        @row-click="go_content"
      >
        <el-table-column prop="time" label="日期" align="center" min-width="25%">
        </el-table-column>
        <el-table-column prop="title" label="标题" align="center" min-width="60%">
        </el-table-column>
      </el-table>
      <br />
      <div
        class="pagination"
        style="margin: 10px 0; display: flex; justify-content: center"
      >
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
      </div>
    </el-col>
    <el-col :span="6"></el-col>
  </el-row>
  <br />
  <br />
  <SysBottom></SysBottom>
</template>

<script>
import SysHeader from "../../components/sys/SysHeader.vue";
import IndexCarousel from "../../components/sys/IndexCarousel.vue";
import SysBottom from "../../components/sys/SysBottom.vue";

import request from "@/utils/request.js";

export default {
  name: "consultList",
  components: {
    SysHeader,
    IndexCarousel,
    SysBottom,
  },
  data() {
    return {
      form: {},
      currentPage4: 1,
      pageSize: 5,
      total: 23,
      Search: "",
      tableData: [],
      dialogVisible: false,
    };
  },
  mounted() {
    this.load();
  },
  methods: {
    load() {
      request
        .get("/medicalInformation/getList", {
          params: {
            pageNum: this.currentPage4,
            pageSize: this.pageSize,
            search: this.Search,
          },
        })
        .then((res) => {
          console.log(res);
          this.tableData = res.data.records;
          this.total = res.data.total;
        });
    },
    /**
     * 转到每一条提问的详情界面
     */
    go_content(row) {
      console.log(row.id);
      // let item = row;
      //使用带值转发，比总线传值更方便
      this.$router.push({
        path: "/medicalInformation/detial",
        query: {
          id: row.id,
        },
      });
    },

    //数据分页
    handleSizeChange(pageSize) {
      //  每页条数改变时触发

      this.pageSize = pageSize;
      this.load();
    },
    handleCurrentChange(pageNum) {
      //点击页码时触发

      this.pageNum = pageNum;
      this.load();
    },
  },
};
</script>

<style scoped>
.search {
  display: flex;
  justify-content: center;
}

.main_body {
  width: 100%;

  background-color: rgb(180, 181, 186);
}
.pagination {
  text-align: center;
}
</style>
