<template>
  <div>
    <div v-if="list.length != 0">
      <div class="slider">
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
  data() {
    return {
      list: [],
      visibleSlide: 0,
    };
  },
  methods: {
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
    getImgUrl(pic) {
      return require("../assets/cvs/" + pic);
    },
    getUnits: function() {
      fetch("http://localhost:8080/api/candidat/all")
        .then((response) => response.json())
        .then((json) => {
          this.list = json;
        });
      // ,
      // fetch("http://localhost:8080/api/candidat/all")
      // .then((response) => response.json())
    },
  },
  created: function() {
    this.getUnits();
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

.slider {
  overflow: hidden;
  margin: 40px auto 0;
  position: relative;
  width: 600px;
  height: 850px;
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
.prev:hover {
  background: #cab245;
}
</style>