<template>
  <div class="main">
    <div class="left">
      <LeftLayout />
    </div>
    <div class="right">
      <div class="content">
        <div class="main_content">
          <transition name="home-pages" mode="out-in">
              <router-view />
          </transition>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import LeftLayout from "./leftLayout.vue";
export default {
  components: {
    LeftLayout,
  },
};
</script>

<style lang="less" scoped>
.home-pages-enter-active,
.home-pages-leave-active {
  transition: all 0.35s;
}
.home-pages-enter {
  opacity: 0;
  transform: translateX(-40px);
}
.home-pages-leave-to {
  opacity: 0;
  transform: translateX(60px);
}
.main {
  --mainColor: #178fff;
  height: 100%;
  width: 100%;
  display: flex;
  .left {
    background: #f8f8f8;
  }
  .right {
    flex: 1;
    display: flex;
    flex-direction: column;
    background-color: #f0f2f5;
    overflow: hidden;
    .content {
      flex: 1;
      overflow-y: auto;
      padding: 18px;
      .main_content {
        background: #fff;
        padding: 18px;
      }
    }
  }
}
</style>
<style lang="less" scoped>
</style>