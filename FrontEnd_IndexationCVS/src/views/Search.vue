<template>
  <div class="overview">
    <div class="header">
      <form>
        <div class="search_wrap">
          <div class="search_box">
            <div class="form-box">
              <input
                type="text"
                class="input"
                placeholder="Enter Keywords ..."
                v-model="content"
              />
              <div class="btn btn_common" v-on:click="searchContent()">
                <i class="fas fa-search">Go</i>
              </div>
            </div>
          </div>
        </div>
      </form>
    </div>

    <div v-if="list.length != 0">
      <div class="slider1">
        <button @click="next" class="next">
          <img src="../assets/icons8-chevron-gauche-50.png" />
        </button>
        <button @click="prev" class="prev">
          <img src="../assets/icons8-chevron-droit-50.png" />
        </button>
        <carousel-slide
          v-for="(slide, index) in list"
          :key="slide"
          :index="index"
          :visibleSlide="visibleSlide"
        >
          <transition name="left">
            <div v-show="visibleSlide === index" class="carouseil-slide">
              <img
                class="img-cv"
                :src="getImgUrl(slide.name)"
                width="100%"
              /></div></transition
        ></carousel-slide>
      </div>
    </div>
  </div>
</template>

<!-- ###########################################################################################################################################################################  -->

<script>
export default {
  data: () => {
    return {
      list: [],
      visibleSlide: 0,
    };
  },
  methods: {
    searchContent() {
      fetch("http://localhost:8080/api/candidat/search/" + this.content)
        .then((response) => response.json())
        .then((json) => {
          this.list = json;
        });
    },
    getImgUrl(pic) {
      return require("../assets/cvs/" + pic);
    },
    next() {
      if (this.visibleSlide >= this.list.length - 1) {
        this.visibleSlide = 0;
      } else {
        this.visibleSlide++;
      }
    },

    prev() {
      if (this.visibleSlide <= 0) {
        this.visibleSlide = this.list.length - 1;
      } else {
        this.visibleSlide--;
      }
    },
  },
};
</script> 

<style>
.left-enter-active {
  animation: leftInAnimation 0.4s ease-in-out;
}

.left-leave-active {
  animation: leftOutAnimation 0.4s ease-in-out;
}

@keyframes leftInAnimation {
  from {
    transform: translateX(100%);
  }
  to {
    transform: translateX(0);
  }
}

@keyframes leftOutAnimation {
  from {
    transform: translateX(0);
  }
  to {
    transform: translateX(-100%);
  }
}

.next {
  outline: none;
  cursor: pointer;
  position: absolute;
  overflow: hidden;
  height: 60px;
  width: 12%;
  top: 50%;
  background-color: rgba(255, 255, 255, 0.1);
  color: rgb(0, 0, 0);
  left: 0;
  border: 1mm ridge rgba(0, 0, 0, 0.6);
  border-radius: 50px;
}

.prev {
  outline: none;
  cursor: pointer;
  position: absolute;
  height: 60px;
  width: 12%;
  top: 50%;
  border: 1mm ridge rgba(0, 0, 0, 0.6);
  background-color: rgba(255, 255, 255, 0.1);
  right: 0;
  border-radius: 30px;
}
.prev:hover {
  background: #cab245;
}
.next:hover {
  background: #cab245;
}

h1 {
  color: white;
  margin-bottom: 70px;
  font-size: 45px;
  text-align: center;

  letter-spacing: 2px;
}
.search-btn {
  cursor: pointer;
}

/*------------------------------------------------------------------------------------------------ */

.slider1 {
  width: 500px;
  height: 700px;
  margin: 100px auto 0;
  position: relative;
  overflow: hidden;
}
.container-slides {
  width: 100%;
  display: flex;
  flex-flow: row nowrap;
}
.img-slider {
  width: 100%;
  height: 100%;
}

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