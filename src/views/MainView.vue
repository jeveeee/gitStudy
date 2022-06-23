<template>
  <div :class="skip == true? 'welcome my-face':'welcome'">{{ user.userName }},欢迎使用报修管理系统～</div>
</template>
<script>
export default {
  name: "NoFound",
  components: {},
  mounted() {
    this.user = JSON.parse(localStorage.getItem("user"));
  },
  data() {
    return {
      user: {},
      skip:true
    };
  },
};
</script>
<style scoped>
.welcome {
  text-align: center;
  line-height: calc(100vh - 60px);
  font-size: 40px;
  color: #3070f0;
}

</style>
