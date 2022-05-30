<template>
  <div class="main">
    <span class="title">404</span>
    <router-link to="/welcome" class="return">去首页</router-link>
    <div class="boat">
      <img src="@/assets/astronaut.svg" class="astr" />
    </div>
    <img src="@/assets/earth.svg" class="earth" />
  </div>
</template>
<style lang="less" scoped>
.main {
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background: linear-gradient(to right, rgb(62, 81, 81), rgb(222, 203, 164));

  .title {
    font-size: 8em;
    font-family: "Courier New", Courier, monospace;
  }
  .return {
    color: #3f5946;
    font-size: 1.4em;
    &:hover {
      text-decoration: underline;
    }
  }
}
.boat {
  position: absolute;
  bottom: 200px;
  right: 200px;
  animation: move-astronaut 20s linear infinite alternate;
  @keyframes move-astronaut {
    100% {
      -webkit-transform: translate(-160px, -160px);
      transform: translate(-160px, -160px);
    }
  }
}
.astr {
  height: 180px;
  animation: rotate-astronaut 30s linear infinite alternate;
  @keyframes rotate-astronaut {
    100% {
      -webkit-transform: rotate(-720deg);
      transform: rotate(-720deg);
    }
  }
}
.earth {
  height: 100px;
  position: absolute;
  top: 200px;
  left: 200px;
}
</style>