<template>
  <div>
    <div class="login"></div>
    <div class="login-form">
      <el-form
        :model="form"
        ref="loginForm"
        label-width="50px"
        class="demo-ruleForm"
      >
        <div class="text-header">登录</div>
        <el-form-item label="账号" prop="username">
          <el-input
            type="text"
            v-model="form.username"
            autocomplete="off"
            placeholder="请输入账号"
          ></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input
            type="password"
            v-model="form.password"
            autocomplete="off"
            placeholder="请输入密码"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button plain type="primary" size="mini" @click="submitForm()"
            >提交</el-button
          >
          <el-button plain @click="resetForm()" size="mini">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { ElMessage } from "element-plus";
import { httpService } from "@/utils/httpService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      form: {
        username: "",
        password: "",
      },
    };
  },
  methods: {
    submitForm() {
      httpService(
        "/api/login",
        "username=" + this.form.username + "&password=" + this.form.password,
        "post"
      ).then((data) => {
        if (data.code == 200) {
          // ElMessage.success(data.msg);
          localStorage.setItem("user", JSON.stringify(data.obj));
          localStorage.setItem("token", data.obj.id);
          this.$router.push({ path: "/indexView" });
        } else {
          ElMessage.error(data.msg);
        }
      });
    },
    resetForm() {
      this.$refs.loginForm.resetFields();
    },
  },
};
</script>

<style scoped >
body {
  padding: 0;
  margin: 0;
}
.text-header {
  text-align: center;
  font-size: 20px;
  /* color: #fff; */
  margin-bottom: 50px;
}
.login {
  width: 100vw;
  height: 100vh;
  background-image: url("../assets/images/desk-g425c531c0_1920.jpg");
  background-repeat: no-repeat;
  background-size: cover;
  /* filter:blur(5px); */
}
.login-form {
  position: absolute;
  width: 400px;
  height: 320px;
  background: #f2f2f2;
  top: 240px;
  right: 300px;
  border-radius: 10px;
  box-shadow: 1px 1px 5px #dddddd;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
