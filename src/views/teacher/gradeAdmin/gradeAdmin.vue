<template>
  <div>
    <el-form :inline="true" :model="form" size="small">
      <el-form-item label="课程">
        <el-select v-model="form.courseId" placeholder="请选择课程">
          <el-option
            v-for="item in courses"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="getList">查询</el-button>
      </el-form-item>
    </el-form>

    <el-table :data="tableData" border v-loading="loading">
      <el-table-column prop="studentId" label="学号" align="center">
      </el-table-column>
      <el-table-column prop="studentName" label="学生姓名" align="center">
      </el-table-column>
      <el-table-column prop="courseId" label="课程号" align="center">
      </el-table-column>
      <el-table-column prop="courseName" label="课程名" align="center">
      </el-table-column>
      <el-table-column prop="grade" label="学生总评成绩" align="center">
        <template slot-scope="scope">
          {{ scope.row.grade ? scope.row.grade : "暂无录入" }}
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="140" align="center">
        <template slot-scope="scope">
          <el-button size="mini" type="text" @click="addGrade(scope.row)"
            >录入成绩</el-button
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
  </div>
</template>

<script>
import { teacherCourse, getGrade, updateGrade } from "@/apis/teacher";
export default {
  data() {
    return {
      tableData: [],
      form: {
        courseId: "",
      },
      courses: [],
      total: 0,
      currentPage: 1,
      pageSize: 10,
      loading: false,
    };
  },
  created() {
    teacherCourse().then((res) => {
      this.courses = res.data;
    });
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
      getGrade({
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
    addGrade(item) {
      this.$prompt(
        `请输入${item.studentName}同学${item.courseName}课程的成绩`,
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
        }
      )
        .then(({ value }) => {
          updateGrade(item.id,value).then(() => {
            this.$message({
              type: "success",
              message: "成绩录入成功",
            });
            this.getList()
          });
        })
        .catch(() => {});
    },
  },
};
</script>

<style>
</style>