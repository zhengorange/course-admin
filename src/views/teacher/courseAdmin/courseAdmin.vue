<template>
  <div>
    <el-form :inline="true" :model="form" size="small">
      <el-form-item label="课程名称">
        <el-input v-model="form.name" placeholder="请输入课程名称"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
      </el-form-item>
      <el-form-item>
        <el-button @click="clear">重置</el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="add">添加课程</el-button>
      </el-form-item>
    </el-form>

    <el-table :data="tableData" border v-loading="loading">
      <el-table-column prop="id" label="课程编号" align="center">
      </el-table-column>
      <el-table-column prop="name" label="课程名称" align="center">
      </el-table-column>
      <el-table-column prop="credits" label="课程学分" align="center">
      </el-table-column>
      <el-table-column prop="maxStudent" label="课程最大人数" align="center">
      </el-table-column>
      <el-table-column prop="time" label="上课时间" align="center">
        <template slot-scope="scope"
          >{{ $getCourseTime(scope.row.time) }}
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="140" align="center">
        <template slot-scope="scope">
          <el-button size="mini" type="text" @click="edit(scope.row)"
            >编辑</el-button
          >
          <el-button
            size="mini"
            type="text"
            style="color: red"
            @click="dele(scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
      <el-empty slot="empty" description="暂无数据"></el-empty>
    </el-table>
    <el-pagination
      v-if="total > 0"
      style="margin-top: 6px; text-align: right"
      :hide-on-single-page="false"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
    >
    </el-pagination>
    <Editer ref="editer" @getList="getList" />
  </div>
</template>

<script>
import { getCourse, deleteCourse } from "@/apis/teacher";
import Editer from "./editer.vue";
export default {
  components: {
    Editer,
  },
  data() {
    return {
      tableData: [],
      form: {
        name: "",
      },
      total: 0,
      currentPage: 1,
      pageSize: 10,
      loading: false,
    };
  },
  created() {
    this.getList();
  },
  methods: {
    handleSizeChange(e) {
      this.pageSize = e;
      this.getList();
    },
    handleCurrentChange(e) {
      this.currentPage = e;
      this.getList();
    },
    getList() {
      this.loading = true;
      getCourse({
        ...this.form,
        current: this.currentPage,
        size: this.pageSize,
      })
        .then((res) => {
          this.total = res.data.total;
          this.tableData = res.data.records;
        })
        .finally(() => {
          this.loading = false;
        });
    },
    onSubmit() {
      this.getList();
    },
    clear() {
      this.currentPage = 1;
      this.pageSize = 10;
      this.form = { name: "" };
      this.getList();
    },
    add() {
      this.$refs.editer.add();
    },
    edit(e) {
      this.$refs.editer.edit({ ...e });
    },
    dele(e) {
      this.$confirm("此操作将永久删除该班级, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        deleteCourse(e.id).then((res) => {
          this.getList();
          this.$message({
            type: "success",
            message: "删除成功!",
          });
        });
      });
    },
  },
};
</script>

<style>
</style>