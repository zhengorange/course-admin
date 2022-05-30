<template>
  <div class="login_layout">
    <div class="back" :class="{ blur: isAuthenLoading }"></div>
    <transition name="authen" mode="out-in">
      <router-view />
    </transition>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  computed: {
    ...mapState(["isAuthenLoading"]),
  }
};
</script>

<style lang="less" scoped>
.authen-enter-active,
.authen-leave-active {
  transition: all 0.2s;
}
.authen-enter {
  opacity: 0;
  transform: translateX(30px);
}
.authen-leave-to {
  opacity: 0;
  transform: translateX(-120px);
}
.login_layout {
  height: 100%;
  display: flex;
  align-items: center;
  position: relative;
  justify-content: center;
  flex-direction: column;
  overflow: hidden;

  .back {
    position: absolute;
    top: -100px;
    bottom: -100px;
    left: -100px;
    right: -100px;
    z-index: -1;
    transition: all 0.5s;
    background: url(~@/assets/bg.jpg);
    background-repeat: no-repeat;
    background-position: center;
    background-size: cover;
    animation: creat 2.5s forwards;
    @keyframes creat {
      0% {
        transform: scale(1.5);
        filter: blur(5px);
      }
      75% {
        transform: scale(0.99);
        filter: blur(3px);
      }
      100% {
        transform: scale(1);
        filter: blur(0);
      }
    }
  }
  .blur {
    top: -150px;
    left: -150px;
    right: -150px;
    bottom: -150px;
  }
}
</style>