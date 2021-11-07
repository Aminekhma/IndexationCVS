<template>
  <div class="dashboard">
    <Sidebar />
    <div class="content">
      <router-view />
    </div>
  </div>
</template>

<script>
import Sidebar from "../components/Sidebar";
export default {
  name: "home",

  components: {
    Sidebar,
  },
};
</script> 

<style>
.dashboard {
  display: grid;
  grid-template-columns: 1fr 5fr;
}

.content {
  min-height: 98vh;
  background: linear-gradient(
    to right bottom,
    rgba(255, 255, 255, 2),
    rgba(245, 228, 200, 0.1)
  );
  border-radius: 80px;
  background-size: cover;

  margin: 6px 6px 6px 0px;
}
</style>