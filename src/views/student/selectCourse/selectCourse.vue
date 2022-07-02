<template>
  <div>
    <div class="top_bar">
      <div v-if="trainName">
        {{ `${trainName}（选修课需修满${electives}分）` }}
      </div>
      <div>
        <el-button type="primary" @click="onSubmit" size="small"
          >刷新可选课程</el-button
        >
      </div>
    </div>
    <el-table :data="tableData" border v-loading="loading">
      <el-table-column prop="courseId" label="课程编号" align="center">
      </el-table-column>
      <el-table-column prop="courseName" label="课程名称" align="center">
      </el-table-column>
      <el-table-column prop="credits" label="课程学分" align="center">
      </el-table-column>
      <el-table-column prop="teacherName" label="任课老师" align="center">
      </el-table-column>
      <el-table-column prop="required" label="是否必修" align="center">
        <template slot-scope="scope">
          {{ scope.row.required ? "必修" : "选修" }}
        </template>
      </el-table-column>
      <el-table-column prop="selectedCount" label="已选人数" align="center">
      </el-table-column>
      <el-table-column prop="maxStudent" label="课程最大人数" align="center">
      </el-table-column>
      <el-table-column label="上课时间" align="center">
        <template slot-scope="scope"
          >{{ $getCourseTime(scope.row.courseTime) }}
        </template>
      </el-table-column>
      <el-table-column label="选课状态" align="center">
        <template slot-scope="scope">
          <el-tag v-if="isSelected(scope.row.courseId)" effect="dark"
            >已选
          </el-tag>
          <el-tag v-else effect="plain"> 未选 </el-tag>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="140" align="center">
        <template slot-scope="scope">
          <el-button
            v-if="isSelected(scope.row.courseId)"
            size="mini"
            type="text"
            style="color: red"
            @click="dele(scope.row)"
            >退课</el-button
          >
          <el-button
            v-else
            size="mini"
            type="text"
            @click="addCourse(scope.row)"
            >选课</el-button
          >
        </template>
      </el-table-column>
      <el-empty slot="empty" description="暂无数据"></el-empty>
    </el-table>
  </div>
</template>

<script>
import {
  allSelectCourse,
  deleteSelectCourse,
  addSelectCourse,
  ableSelectCourse,
} from "@/apis/student";

export default {
  data() {
    return {
      tableData: [],
      trainName: "",
      electives: "",
      selectedList: [],
      loading: false,
    };
  },
  created() {
    this.getList();
  },
  methods: {
    getList() {
      this.loading = true;
      allSelectCourse().then((res) => {
        this.selectedList = res.data;
      });
      ableSelectCourse()
        .then((res) => {
          this.trainName = res.data.name;
          this.electives = res.data.electives;
          this.tableData = res.data.trainingItems;
        })
        .finally(() => {
          this.loading = false;
        });
    },
    isSelected(courseId) {
      let obj = this.selectedList.find((e) => {
        return e["courseId"] == courseId;
      });
      return obj && true;
    },
    onSubmit() {
      this.getList();
    },
    addCourse(item) {
      this.$confirm(`此操作将选${item.teacherName}老师的${item.courseName}, 是否继续?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        addSelectCourse({
          courseId: item.courseId
        }).then(() => {
          this.getList();
          this.$message({
            type: "success",
            message: "选课成功!",
          });
        });
      });
    },
    dele(item) {
      const id = this.selectedList.find(e=>{
        return e['courseId'] == item.courseId
      }).id
      this.$confirm(`此操作将退${item.teacherName}老师的${item.courseName}, 是否继续?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        deleteSelectCourse(id).then(() => {
          this.getList();
          this.$message({
            type: "success",
            message: "退课成功!",
          });
        });
      });
    },
  },
};
</script>

<style lang="less" scoped>
.top_bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 10px 15px 10px;
  > div:first-child {
    font-size: large;
    font-weight: bold;
  }
}
.item {
  margin-top: 10px;
  margin-right: 40px;
}
</style>