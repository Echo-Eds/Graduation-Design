<template>
  <div class="Location">
    <div class="info_title">
      <span>My Orders</span>
      <br />
      <span>我的订单</span>
    </div>
    <br />
    <br />
    <div class="myLocat">
      <el-table :data="tableData" border style="width: 100%">
        <el-table-column prop="no" label="订单号"></el-table-column>
        <el-table-column prop="time" label="下单时间" />
        <el-table-column prop="state" label="状态" />

        <el-table-column label="操作" align="center">
          <template #default="scope">
            <el-button color="#eebe77" @click="handleFind(scope.row.no, scope.row.state)"
              >查看订单详情</el-button
            >
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
      tableData: [],
      currentPage4: 1,
      pageSize: 5,
      total: 23,
      user: JSON.parse(sessionStorage.getItem("user")),
    };
  },
  mounted() {
    this.load();
  },
  methods: {
    handleDelete(index, id) {
      request.delete("prfInfo/deleteRep/" + id).then((res) => {
        console.log(res);
        if (res.code == "0") {
          this.$message({
            type: "success",
            message: "回复成功！",
            showClose: true,
            center: true,
          });
        } else {
          this.$message({
            type: "error",
            message: res.msg,
            showClose: true,
            center: true,
          });
        }
      });
      this.load();
    },
    load() {
      this.$nextTick(() => {
        // console.log(this.user.id);
        request
          .get("/cart/selectAllMyOrders", {
            params: {
              userId: this.user.id,
              pageNum: this.currentPage4,
              pageSize: this.pageSize,
            },
          })
          .then((res) => {
            console.log(res);
            this.tableData = res.data.records;
            this.total = res.data.total;
          });
      });
    },
    handleFind(no, state) {
      if (state == "未支付") {
        this.$router.push({
          path: "/order",
          query: {
            orderNo: no,
          },
        });
      }
      if (state == "已支付") {
        this.$router.push({
          path: '/orderPayed',
          query: {
            orderNo: no,
          }
        })
      }
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
