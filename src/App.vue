<template>
  <div id="app">
      <transition name="log-home" mode="out-in">
        <router-view />
      </transition>
    </div>
</template>


<style>
.log-home-enter-active,
.log-home-leave-active {
  transition: all .8s;
}
.log-home-enter {
  opacity: 0;
}
.log-home-leave-to {
  opacity: 0;
  transform: translateY(-100vh);
}
html {
  height: 100%;
  width: 100%;
  padding: 0;
}
body {
  height: 100%;
  width: 100%;
  margin: 0;
  padding: 0;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  height: 100%;
}
</style>
