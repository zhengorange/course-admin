<template>
  <el-drawer :title="title" :visible.sync="drawer" direction="rtl" :size="500">
    <div class="form_area">
      <el-form
        :model="form"
        size="small"
        label-position="right"
        label-width="100px"
      >
        <el-form-item label="班级名称">
          <el-input v-model="form.name" placeholder="请输入班级名称"></el-input>
        </el-form-item>
      </el-form>
    </div>
    <div class="but_group">
      <el-button type="primary" size="small" @click="submit" :loading="loading">确认</el-button>
      <el-button size="small" @click="drawer = false">取消</el-button>
    </div>
  </el-drawer>
</template>

<script>
import { saveClass } from  "@/apis/secretary";
export default {
  data() {
    return {
      form: {
        name: "",
      },
      drawer: false,
      title: "",
      loading: false
    };
  },
  methods: {
    add() {
      this.title = "添加班级";
      this.form = {
        name: "",
      };
      this.drawer = true;
    },
    edit(obj) {
      this.title = "编辑班级";
      this.form = obj;
      this.drawer = true;
    },
    submit() {
      this.loading = true
      saveClass(this.form).then(() => {
         this.$notify({type: 'success', message: '操作成功'})
         this.$emit("getList")
         this.loading = false
         this.drawer = false
      });
    },
  },
};
</script>

<style lang="less" scoped>
.form_area {
  padding: 10px;
}
.but_group {
  position: absolute;
  text-align: right;
  left: 0;
  right: 0;
  bottom: 0;
  padding: 10px;
  box-shadow: 0 -3px 20px 2px rgba(128, 128, 128, 0.534);
}
</style>