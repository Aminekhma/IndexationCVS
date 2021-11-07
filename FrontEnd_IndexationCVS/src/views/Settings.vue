<template>
    <div>
        <div v-if="cond1 == 2">
    
            <div class="load">
                <div class="container2">
                    <div>Data refresh in process {{ percent }}%</div>
                    <div class="loading-bar">
                        <div class="percentage" :style="{'width' : percentage + '%'}"></div>
                    </div>
                </div>
            </div>
        </div>
    
        <button @click="refreshData()" class="refresh-button"> Refresh Data</button>
    
        <div v-if="cond1 == 0 ">
    
            <div v-if="list.length != 0">
                <div class="slider">
                    <button @click="next" class="next">
          <img src="../assets/icons8-chevron-gauche-50.png" /></button>
    
                    <button @click="prev" class="prev">
          <img src="../assets/icons8-chevron-droit-50.png" /></button>
                    <carousel-slide v-for="(slide, index) in list" :key="slide" :index="index" :visibleSlide="visibleSlide">
                        <transition name="left">
                            <div v-show="visibleSlide === index" class="carouseil-slide">
                                <img class="img-cv" :src=getImgUrl(slide.name) width="100%" />
                            </div>
                        </transition>
                    </carousel-slide>
                </div>
            </div>
        </div>
    </div>
</template>

<!-- ###########################################################################################################################################################################  -->

<script>
import axios from 'axios';
export default {
  props: {
      list: Array,
      likes: Number,
      isPublished: Boolean,
      commentIds: Array,
      author: Object,
      callback: Function,
      contactsPromise: Promise // ou n'importe quel autre constructeur
  },

  data() {

      return {
          visibleSlide: 0,
          percentage: 0,
          cond1: 1
      }
  },

  computed: {
      percent() {
          return this.percentage.toFixed();
      }
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
          return require('../assets/cvs/' + pic)
      },
      refreshData() {
          this.cond1 = 2;
          var intval = setInterval(() => {

              if (this.percentage < 100) {
                  this.percentage += 0.1;
              } else {
                  this.cond1 = 0
                  clearInterval(intval);
              }

          }, 10);
          axios.post(`http://localhost:8080/api/candidat/addAll`, {}, )
      },
      getUnits: function() {

          fetch("http://localhost:8080/api/candidat/all")
              .then((response) => response.json())
              .then(json => {
                  this.list = json

              })
      }
  },

  created:

      function() {
          this.getUnits()
      }
};
</script> 

<style lang="scss" scoped>
.load {
    font-family: Arial, Helvetica, sans-serif;
    display: flex;
    justify-content: center;
    align-items: center;
    bottom: 0;
    position: absolute;
    top: -1%;
    left: 45%;
}

.container2 {
    text-align: right;
    font-size: 1rem;
    color: #555;
}

.loading-bar {
    position: relative;
    width: 400px;
    height: 30px;
    border-radius: 15px;
    overflow: hidden;
    border-bottom: 1px solid #ddd;
    box-shadow: inset 0 1px 2px rgba($color: #000, $alpha: .4), 0 -1px 1px #fff, 0 1px 0 #fff;
    .percentage {
        position: absolute;
        top: 1px;
        left: 1px;
        right: 1px;
        display: block;
        height: 100%;
        border-radius: 15px;
        background-color: #a5df41;
        background-size: 30px 30px;
        background-image: linear-gradient(135deg, rgba($color: #fff, $alpha: .15) 25%, transparent 25%, transparent 50%, rgba($color: #fff, $alpha: .15) 50%, rgba($color: #fff, $alpha: .15) 75%, transparent 75%, transparent);
        animation: animate-stripes 3s linear infinite;
    }
}

@keyframes animate-stripes {
    0% {
        background-position: 0 0;
    }
    100% {
        background-position: 60px 0;
    }
}

.refresh-button {
    height: 250px;
    width: 12%;
    top: 52%;
    left: 31%;
    outline: none;
    cursor: pointer;
    background: #000;
    position: absolute;
    border-radius: 35px;
    transform: translate(-50%, -50%);
    color: white;
    font-size: 20px;
}

.refresh-button:hover {
    background-color: rgb(102, 100, 100);
}

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

.next:hover {
    background: #cab245;
}
</style>