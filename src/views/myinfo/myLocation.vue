<template>
  <div class="Location">
    <div class="info_title">
      <span>MyShoppingAddress</span>
      <br />
      <span>我的收货地址</span>
    </div>
    <br />
    <br />
    <div class="myLocat">
      <el-table :data="tableData" border style="width: 100%">
        <el-table-column
          prop="addressid"
          label="地址序号"
          width="180"
        ></el-table-column>
        <el-table-column prop="name" label="收件人" width="180" />
        <el-table-column prop="phoneNum" label="电话" width="180" />
        <el-table-column prop="address" label="收货地址" />
        <el-table-column label="操作">
          <template #default="scope">
            <el-button size="small" @click="handleEdit(scope.$index, scope.row)"
              >编辑</el-button
            >
            <el-popconfirm
              title="确定删除？"
              @confirm="handleDelete(scope.$index, scope.row.addressid)"
            >
              <template #reference>
                <el-button size="small" type="danger">删除</el-button></template
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
      <el-button @click="add">add</el-button>
    </div>

    <!-- 提交表单 -->
    <el-dialog v-model="dialogVisible" title="新增用户" width="30%">
      <el-form :model="form" label-width="120px">
        <el-form-item v-model="form.userid"> </el-form-item>
        <el-form-item label="收件人" style="width: 80%">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="电话" style="width: 80%">
          <el-input v-model="form.phoneNum"></el-input>
        </el-form-item>
        <el-form-item label="地址" style="width: 80%">
          <el-input v-model="form.address" type="textarea"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">Cancel</el-button>
          <el-button type="primary" @click="save">确定</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import request from "@/utils/request.js";
export default {
  name: "MyLocation",
  data() {
    return {
      form: {}, //用于接口
      dialogVisible: false,
      tableData: [],
      currentPage4: 1,
      pageSize: 5,
      total: 23,
      // userId: "",
      userform: {}, //用于存储当前user数据
    };
  },
  created() {
    this.load();
  },
  methods: {
    load() {
      //从内存里取出当前userid
      this.userform = JSON.parse(sessionStorage.getItem("user" || "{}"));
      console.log(this.userform);
      // this.userId = this.userform.id;
      //把userid给用户要提交的表单，便于设置数据库外键
      // this.form.userid = this.userId;
      this.form.userid = JSON.parse(this.userform.id)
      console.log("----当前用户id为：------");
      console.log(this.form.userid);

      request
        .get("/address", {
          params: {
            pageNum: this.currentPage4,
            pageSize: this.pageSize,
            userId: this.form.userid,
          },
        })
        .then((res) => {
          // console.log(res);
          this.tableData = res.data.records;
          this.total = res.data.total;
        });
    },
    save() {
      if (this.form.addressid) {
        request.put("/address", this.form).then((res) => {
          console.log("-----------------------");
          console.log(res);

          // 成功弹窗
          if (res.code == "0") {
            this.$message({
              type: "success",
              message: "修改成功",
            });
          } else {
            this.$message({
              type: "error",
              message: res.msg,
            });
          }
        });
        this.load();
        this.dialogVisible = false;
      } else {
        request.post("/address", this.form).then((res) => {
          console.log(res);
          //新增成功提示信息
          if (res.code == "0") {
            this.$message({
              type: "success",
              message: "新增成功",
            });
          } else {
            this.$message({
              type: "error",
              message: res.msg,
            });
          }
        });
        this.load();
        this.dialogVisible = false;
      }
    },
    handleDelete(index, addressid) {
      console.log(addressid);
      //使用delete方法 把name拼接到user之后，去后端调用delete接口
      request.delete("/address/" + addressid).then((res) => {
        console.log(res);
        if (res.code == 0) {
          this.$message({
            type: "success",
            message: "delete successfully",
          });
        } else {
          this.$message({
            type: "error",
            message: res.msg,
          });
        }
      });
      this.load();
    },
    add() {
      this.dialogVisible = true;
      // this.form = {};
      console.log("------当前addressid为：-----");
      console.log(this.form.addressid);
      console.log("------当前用户id为：-----");
      console.log(this.form.userid);
    },
    handleEdit(index, row) {
      //深拷贝
      this.form = JSON.parse(JSON.stringify(row));
      this.dialogVisible = true;
      console.log(this.form.addressid);
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
