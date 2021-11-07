<template>
  <div id="app">
    <router-view></router-view>
    <div class="imgSU"></div>
  </div>
</template>

<script>
export default {
  name: "App",
};
</script> 

<style>
#app {
  font-family: "Roboto", sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}

body {
  margin: 0px;
  padding: 0px;
  box-sizing: border-box;
  background: url(./views/wallpaper/image2.jpg);
  background-size: cover;
}

.imgSU {
  background-image: url("./assets/logoSU.jpg");
  width: 180px;
  height: 100px;
  background-size: cover;
  position: absolute;

  bottom: 25px;
  left: 40px;
}
</style>