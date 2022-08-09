<template>
  <div class="Location">
    <div class="info_title">
      <span>My collection</span>
      <br />
      <span>我的收藏</span>
    </div>
    <br />
    <br />
    <div class="myLocat">
      <el-table :data="tableData" border style="width: 100%">
        <el-table-column prop="time" label="时间"></el-table-column>
        <el-table-column prop="title" label="文章标题" />

        <el-table-column label="操作" align="center">
          <template #default="scope">
            <el-popconfirm
              title="确定取消收藏？"
              @confirm="handleDelete(scope.$index, scope.row.id)"
            >
              <template #reference>
                <el-button color="#eebe77">取消收藏</el-button></template
              >
            </el-popconfirm>
          </template>
        </el-table-column>
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
  </div>
</template>

<script>
import request from "@/utils/request.js";
export default {
  name: "",
  data() {
    return {
      form: {}, //用于接口

      tableData: [],
      currentPage4: 1,
      pageSize: 5,
      total: 23,
      // userId: "",
      userform: {}, //用于存储当前user数据
    };
  },
  mounted() {
    this.load();
  },
  methods: {
    handleDelete(index, titleId) {
      console.log(titleId);
      request.delete("myCollection/delete/" + titleId).then((res) => {
        console.log(res);
        if (res.code == 0) {
          this.$message({
            type: "success",
            message: "取消成功",
          });
          this.load();
        } else {
          this.$message({
            type: "error",
            message: res.msg,
          });
        }
      });
      
    },
    load() {
      this.userform = JSON.parse(sessionStorage.getItem("user" || "{}"));
      console.log(this.userform);
      this.form.userid = JSON.parse(this.userform.id);
      console.log("----当前用户id为：------");
      console.log(this.form.userid);
      request
        .get("/myCollection/getMyCollection", {
          params: {
            userId: this.form.userid,
          },
        })
        .then((res) => {
          console.log(res);
          this.tableData = res.data;
          this.total = res.data.length;
        });
      /**
       * 设置分页
       */
      request.get("/myCollection/budges", {
        params: {
          pageNum: this.currentPage4,
          pageSize: this.pageSize,
        },
      });
    },
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
.Location {
  width: 900px;
  margin: 100px auto;
  text-align: center;
}
.info_title span {
  display: flex;
  justify-content: center;
  font-size: 24px;
  font-weight: 400;
}
.myLocat {
  height: 400px;
}
</style>
