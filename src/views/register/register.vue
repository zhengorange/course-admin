<template>
  <div class="form">
    <div class="title">用户注册</div>
    <el-form :model="form" status-icon :rules="rules" ref="ruleForm">
      <el-form-item prop="type" label="用户类型：" label-width="50px;">
        <el-select
          v-model="form.type"
          placeholder="请选择用户类型"
          style="width: 80%"
        >
          <el-option label="学生" :value="1"></el-option>
          <el-option label="老师" :value="2"></el-option>
          <el-option label="教学秘书" :value="3"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item
        prop="gender"
        label="用户性别："
        label-width="50px;"
        v-if="form.type == 2 || form.type == 1"
      >
        <el-select
          v-model="form.gender"
          placeholder="请选择用户性别"
          style="width: 80%"
        >
          <el-option label="男" :value="1"></el-option>
          <el-option label="女" :value="2"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item prop="classId" label="所属班级：" v-if="form.type == 1">
        <el-select
          v-model="form.classId"
          placeholder="请选择班级"
          style="width: 80%"
        >
          <el-option
            v-for="item in classes"
            :label="item.name"
            :value="item.id"
            :key="item.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item prop="name">
        <el-input
          v-model="form.name"
          placeholder="你的名字"
          prefix-icon="el-icon-user"
        ></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input
          type="password"
          v-model="form.password"
          placeholder="密码"
          prefix-icon="el-icon-lock"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" :loading="loading" @click="submitForm()"
          >注册</el-button
        >
        <el-button @click="resetForm()">重置</el-button>
        <el-button @click="$router.push({ path: '/login' })"
          >返回登录</el-button
        >
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { allClass, register } from "@/apis";
import md5 from 'js-md5'
export default {
  data() {
    return {
      form: {
        name: undefined,
        gender: undefined,
        classId: undefined,
        password: undefined,
        type: 1,
      },
      checked: false,
      rules: {
        name: [{ required: true, message: "此项不能为空", trigger: "blur" }],
        password: [
          { required: true, message: "此项不能为空", trigger: "blur" },
        ],
      },
      loading: false,
      classes: [],
    };
  },
  created() {
    allClass().then((res) => {
      this.classes = res.data;
    });
  },
  methods: {
    submitForm() {
      this.$refs.ruleForm.validate((valid) => {
        if (valid) {
          this.loading = true;
          register({
            ...this.form,
            password: md5(this.form.password),
          })
            .then((res) => {
              let name = ["学号", "老师编号", "教学秘书编号"][
                this.form.type - 1
              ];
              this.$alert(
                `系统自动生成的${name}为${res.data.id}`,
                "用户注册成功",
                {
                  showClose: false,
                  callback: () => {
                    this.$router.push({ path: "/login" });
                  },
                }
              );
            })
            .finally(() => {
              this.loading = false;
            });
        }
      });
    },
    resetForm() {
      this.$refs.ruleForm.resetFields();
    },
  },
};
</script>

<style lang="less" scoped>
.title {
  font-size: 2em;
  margin-bottom: 1em;
}
.form {
  width: 420px;
  padding: 30px;
  padding-bottom: 15px;
  background-color: #f8f8f8e0;
  border-radius: 12px;
  text-align: center;
  box-shadow: 2px 1px 12px 2px rgba(224, 224, 224, 0.466);
}
.item {
  text-align: left;
  margin-left: 10px;
}
</style>