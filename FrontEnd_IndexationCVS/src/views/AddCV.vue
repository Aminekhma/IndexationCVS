<template>
  <div>
    <div class="search_wrap">
      <div class="search_box">
        <div class="form-box">
          <input
            type="text"
            class="input"
            placeholder="Enter a correct path ..."
            v-model="content"
          />
          <div class="btn btn_common" v-on:click="addCV()">
            <i class="fas fa-search">Add</i>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      id: null,
    };
  },

  methods: {
    addCV() {
      const chemin = { chemin: this.content };
      axios
        .post("http://localhost:8080/api/candidat/add", chemin)
        .then((response) => console.log(response));
    },
  },
};
</script> 

<style>
/*------------------------------------------------------------------------------------------------ */

.container .input {
  border: 0;
  outline: none;
  color: black;
}

.search_wrap {
  width: 500px;
  margin: 30px auto;
}

.search_wrap .search_box {
  position: relative;
  width: 500px;
  height: 60px;
}

.search_wrap .search_box .input {
  position: absolute;
  top: 0;
  left: 0;
  width: 85%;
  height: 30%;
  padding: 10px 20px;
  border: black 1px solid;
  border-radius: 50px;
}

.search_wrap .search_box .btn {
  position: absolute;
  top: 0;
  width: 100px;
  height: 40px;
  background: #c2980f;
  z-index: 1;
  cursor: pointer;
  right: 0;
  border-bottom-right-radius: 3px;
  border-top-right-radius: 3px;
  border-radius: 50px;
  display: flex;
}

.search_wrap .search_box .btn:hover {
  background: #cab245;
}

.search_wrap .search_box .btn.btn_common {
  left: 74%;
}

.search_wrap .search_box .btn.btn_common .fas {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  color: #fff;
  font-size: 20px;
}
</style>