<template>
  <div>
    <el-table :data="tableData" stripe>
        <el-table-column prop="id" label="ID" />
        <el-table-column prop="username" label="用户名" />

        <el-table-column prop="nickName" label="昵称" />
        <el-table-column prop="age" label="年龄" />
        <el-table-column prop="sex" label="性别" />
        <el-table-column prop="address" label="地址" />
        <!-- 操作 -->
        <el-table-column label="操作">
          <template #default="scope">
            <el-button size="small" @click="handleEdit(scope.$index, scope.row)"
              >编辑</el-button
            >
            <el-popconfirm
              title="确定删除？"
              @confirm="handleDelete(scope.$index, scope.row.id)"
            >
              <template #reference>
                <el-button size="small" type="danger">删除</el-button></template
              >
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
  </div>
</template>

<script>
export default {
  name:'Table',
  data(){
   return {

   }
  }
}
</script>

<style scoped>

</style>
