<template>
  <el-dialog v-model="dialogFormVisible" :title="title" @close="closeDialog">
    <el-form
      :model="addSysUserForm"
      ref="addSysUserRef"
      :rules="addSysUserrules"
    >
      <el-form-item
        label="用户名"
        :label-width="formLabelWidth"
        prop="userName"
      >
        <el-input
          v-model="addSysUserForm.userName"
          autocomplete="off"
          ref="name"
          :readonly="addSysUserForm.id > 0"
          placeholder="请填写用户名"
        ></el-input>
      </el-form-item>
      <el-form-item
        label="联系电话"
        :label-width="formLabelWidth"
        prop="mobile"
      >
        <el-input
          v-model="addSysUserForm.mobile"
          autocomplete="off"
          ref="mobile"
          placeholder="请填写联系电话"
        ></el-input>
      </el-form-item>
      <el-form-item
        label="用户类型"
        :label-width="formLabelWidth"
        prop="userType"
      >
        <el-select v-model="addSysUserForm.userType" placeholder="请选择类型">
          <el-option label="管理员" :value="1"></el-option>
          <el-option label="报修员" :value="2"></el-option>
          <el-option label="维修员" :value="3"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item
        label="是否可用"
        :label-width="formLabelWidth"
        prop="isDelete"
        v-if="addSysUserForm.id"
      >
        <el-select
          v-model="addSysUserForm.isDelete"
          placeholder="请选择是否可用"
        >
          <el-option label="可用" :value="0"></el-option>
          <el-option label="禁用" :value="1"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item
        label="密码"
        :label-width="formLabelWidth"
        prop="userPassword"
      >
        <el-input
          v-model="addSysUserForm.userPassword"
          autocomplete="off"
          ref="name"
          type="password"
          placeholder="请填写密码"
        ></el-input>
      </el-form-item>
      <el-form-item
        label="确认密码"
        :label-width="formLabelWidth"
        prop="rePassword"
      >
        <el-input
          v-model="addSysUserForm.rePassword"
          autocomplete="off"
          type="password"
          placeholder="请在此填写密码"
        ></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="cancel()">取消</el-button>
        <el-button type="primary" @click="save()">保存</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script>
import { ref } from "vue";
import { httpService } from "@/utils/httpService";
import { ElMessage, ElForm } from "element-plus";
import { vxRule } from "@/utils/validator";

export default {
  name: "AddSysUser",
  components: { ElForm, ElMessage },
  data() {
    return {
      dialogFormVisible: ref(false),
      formLabelWidth: "120px",
      title: "",
      personnelType: "",
      addSysUserForm: {
        userName: "",
        userType: "",
        userPassword: "",
        isDelete: "",
        campus: "",
        rePassword: "",
        mobile: "",
      },
      // 校验规则
      addSysUserrules: {
        userName: vxRule(true, null, "blur"),
        userType: vxRule(true, null, "blur"),
        isDelete: vxRule(true, null, "blur"),
        mobile: vxRule(true, null, "blur"),
      },
    };
  },
  methods: {
    closeDialog() {
      this.clear();
    },
    cancel() {
      this.dialogFormVisible = false;
      this.clear();
    },
    getSysUserById(id) {
      httpService("/api/sysUser/queryById", { id: id }, "get").then((data) => {
        data.userPassword = "";
        data.rePassword = "";
        this.addSysUserForm = data;
      });
    },
    clear() {
      this.dialogFormVisible = false;
      //清空form
      this.addSysUserForm.id = "";
      this.addSysUserForm.userName = "";
      this.addSysUserForm.userType = "";
      this.addSysUserForm.userPassword = "";
      this.addSysUserForm.rePassword = "";
      this.addSysUserForm.isDelete = "";
      this.addSysUserForm.campus = "";
      this.addSysUserForm.mobile = "";
    },
    save() {
      this.$refs.addSysUserRef.validate((valid) => {
        if (valid) {
          let params = new FormData(); //创建一个form对象,必须是form对象否则后端接受不到数据
          params.append("userName", this.addSysUserForm.userName);
          params.append("userType", this.addSysUserForm.userType);
          params.append("userPassword", this.addSysUserForm.userPassword);
          params.append("isDelete", this.addSysUserForm.isDelete);
          params.append("mobile", this.addSysUserForm.mobile);

          if (
            this.addSysUserForm.userPassword != this.addSysUserForm.rePassword
          ) {
            ElMessage.error("两次密码输入不一致");
            return;
          }
          if (this.addSysUserForm.id) {
            params.append("id", this.addSysUserForm.id);
            httpService("/api/sysUser/edit", params, "post").then((data) => {
              if (data.code == 200) {
                ElMessage.success("修改成功");
                this.clear();
                //刷新父组件的数据
                this.$parent.getSysUserList();
              } else {
                ElMessage.error("修改失败");
              }
            });
          } else {
            httpService("/api/sysUser/add", params, "post").then((data) => {
              if (data.code == 200) {
                ElMessage.success("添加成功");
                this.clear();
                //刷新父组件的数据
                this.$parent.getSysUserList();
              } else {
                ElMessage.error(data.msg);
              }
            });
          }
        } else {
          return false;
        }
      });
    },
  },
};
</script>
<style>
.el-select {
  width: 100%;
}
</style>