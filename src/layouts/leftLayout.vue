<template>
  <div class="main">
    <div class="sys">XX教务系统</div>
    <el-menu
      class="menu"
      default-active="1"
      @select="handleOpen"
      text-color="#fff"
      :unique-opened="true"
    >
      <el-menu-item v-for="item in myMenu" :key="item.name" :index="item.index">
        <span slot="title">{{ item.name }}</span>
      </el-menu-item>
    </el-menu>

    <div class="user">
      <el-popover placement="right-start" trigger="hover">
        <div class="cont" v-if="type == 1">
          <div>
            学号：<strong>{{ user.id }}</strong>
          </div>
          <div>
            性别：<strong>{{ user.gender == 1 ? "男" : "女" }}</strong>
          </div>
          <div>
            姓名：<strong>{{ user.name }}</strong>
          </div>
          <div>
            所在班级：<strong>{{ user.className }}</strong>
          </div>
          <div class="item" @click="quit">退出登录</div>
        </div>
        <div class="cont" v-if="type == 2">
          <div>
            老师编号：<strong>{{ user.id }}</strong>
          </div>
          <div>
            性别：<strong>{{ user.gender == 1 ? "男" : "女" }}</strong>
          </div>
          <div>
            姓名：<strong>{{ user.name }}</strong>
          </div>
          <div class="item" @click="quit">退出登录</div>
        </div>
        <div class="cont" v-if="type == 3">
          <div>
            教学秘书编号：<strong>{{ user.id }}</strong>
          </div>
          <div>
            姓名：<strong>{{ user.name }}</strong>
          </div>
          <div class="item" @click="quit">退出登录</div>
        </div>
        <span class="card" slot="reference">
          <el-avatar :size="36" icon="el-icon-user-solid"></el-avatar>
          <span class="name">{{ user.name }}</span>
        </span>
      </el-popover>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      menu: [
        [
          {
            name: "首页",
            url: "",
            index: "1",
          },
          {
            name: "查看课表",
            url: "",
            index: "2",
          },
          {
            name: "选课",
            url: "",
            index: "3",
          },
          {
            name: "查看成绩",
            url: "",
            index: "4",
          },
        ],
        [
          {
            name: "首页",
            url: "",
            index: "1",
          },
          {
            name: "课程管理",
            url: "",
            index: "2",
          },
          {
            name: "成绩管理",
            url: "",
            index: "3",
          },
        ],
        [
          {
            name: "首页",
            url: "",
            index: "1",
          },
          {
            name: "培养方案管理",
            url: "",
            index: "2",
          },
          {
            name: "学生管理",
            url: "",
            index: "3",
          },
          {
            name: "班级管理",
            url: "",
            index: "4",
          },
          {
            name: "选课管理",
            url: "",
            index: "5",
          },
        ],
      ],
    };
  },
  computed: {
    type() {
      return sessionStorage.getItem("type") ?? localStorage.getItem("type");
    },
    myMenu() {
      let type = sessionStorage.getItem("type") ?? localStorage.getItem("type");
      return this.menu[type - 1];
    },
    user() {
      return JSON.parse(
        sessionStorage.getItem("user") ?? localStorage.getItem("user")
      );
    },
  },
  methods: {
    handleOpen(e) {
      console.log(e);
    },
    quit() {
      sessionStorage.clear();
      localStorage.clear();
      this.$message.success("退出成功");
      setTimeout(() => {
        this.$router.push({
          name: "login",
        });
      }, 400);
    },
  },
};
</script>
<style lang="less" scoped>
.main {
  display: flex;
  flex-direction: column;
  background: #304156;
}
.sys {
  padding: 16px;
  font-size: 1.2em;
  font-weight: 600;
  color: rgb(240, 242, 245);
  text-align: center;
  margin: 0 auto;
}
::-webkit-scrollbar {
  width: 0;
  height: 0;
  color: transparent;
  display: none;
}
.menu {
  border-right: 0;
  width: 160px;
  /deep/.el-menu-item {
    background: #304156;
    &:hover {
      background: #304156da;
    }
  }
  /deep/.el-submenu__title {
    background: #304156da;
  }
  /deep/.el-submenu {
    background: #304156;
    &:hover {
      background: #304156da;
    }
  }
  /deep/.el-menu-item.is-active {
    background: #409eff;
    color: #fff;
  }
  /deep/.el-submenu .el-menu-item {
    min-width: 0;
  }
}
.user {
  margin-top: auto;
  padding: 10px 0;
  display: flex;
  justify-content: center;
  .card {
    display: flex;
    align-items: center;
  }
  .name {
    font-family: Lato, PingFang SC, Microsoft YaHei, sans-serif;
    color: #dfdfdf;
    margin: 0 8px;
    font-weight: bold;
    font-size: 1.1em;
  }
}
.cont {
  display: flex;
  flex-direction: column;
  align-items: center;
  > div:not(:last-child) {
    margin-bottom: 5px;
  }
}
.item {
  transition: all 0.8s;
  cursor: pointer;
  user-select: none;
  &:hover {
    color: #178fff;
  }
}
</style>