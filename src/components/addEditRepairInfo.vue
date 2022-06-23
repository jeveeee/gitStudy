<template>
  <el-dialog v-model="dialogFormVisible" :title="title" @close="closeDialog">
    <el-form
      :model="addRepairInfoForm"
      ref="addRepairInfoRef"
      :rules="addRepairInforules"
    >
      <el-form-item
        label="报修类型"
        :label-width="formLabelWidth"
        prop="type"
        v-model="addRepairInfoForm.type"
      >
        <el-select
          v-model="addRepairInfoForm.type"
          placeholder="请选择报修类型"
          clearable
        >
          <el-option label="电教报修" :value="1"></el-option>
          <el-option label="总务报修" :value="2"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item
        label="报修状态"
        :label-width="formLabelWidth"
        prop="status"
        v-model="addRepairInfoForm.status"
      >
        <el-select
          v-model="addRepairInfoForm.status"
          placeholder="请选择报修状态"
          clearable
        >
          <el-option label="新报修" :value="1"></el-option>
          <el-option label="维修中" :value="2"></el-option>
          <el-option label="已完成" :value="3"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item
        label="申请人姓名"
        :label-width="formLabelWidth"
        prop="applicantName"
      >
        <el-input
          v-model="addRepairInfoForm.applicantName"
          autocomplete="off"
          ref="name"
          placeholder="请填写申请人姓名"
        ></el-input>
      </el-form-item>
      <el-form-item
        label="联系电话"
        :label-width="formLabelWidth"
        prop="mobile"
      >
        <el-input
          v-model="addRepairInfoForm.mobile"
          autocomplete="off"
          ref="name"
          placeholder="请填写申请人联系电话"
        ></el-input>
      </el-form-item>
      <el-form-item
        label="报修地点"
        :label-width="formLabelWidth"
        prop="address"
      >
        <el-input
          v-model="addRepairInfoForm.address"
          autocomplete="off"
          ref="name"
          placeholder="请填写报修地点"
        ></el-input>
      </el-form-item>
      <el-form-item
        label="故障描述"
        :label-width="formLabelWidth"
        prop="description"
      >
        <el-input
          v-model="addRepairInfoForm.description"
          autocomplete="off"
          ref="name"
          placeholder="请填写故障描述"
        ></el-input>
      </el-form-item>

      <el-form-item label="备注" :label-width="formLabelWidth" prop="remarks">
        <el-input
          v-model="addRepairInfoForm.remarks"
          autocomplete="off"
          ref="name"
          placeholder="请填写备注"
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
  name: "AddRepairInfo",
  components: { ElForm, ElMessage },
  data() {
    return {
      dialogFormVisible: ref(false),
      formLabelWidth: "120px",
      title: "",
      personnelType: "",
      addRepairInfoForm: {
        type: "",
        status: "",
        repairDate: "",
        address: "",
        description: "",
        imageUrl: "",
        message: "",
        evaluate: "",
        reason: "",
        consumables: "",
        applicantName: "",
        remarks: "",
        mobile: "",
        star: "",
        radioUrl: "",
        evaluateDate: "",
        openid: "",
        campus: "",
        finishTime: "",
        allocatedTime: "",
        detail: "",
        repairCancelReason: "",
        repairCancelReasonRemark: "",
        repairCancelDate: "",
        location: "",
        level: "",
      },
      // 校验规则
      addRepairInforules: {
        address: vxRule(true, null, "blur"),
        status: vxRule(true, null, "blur"),

        description: vxRule(true, null, "blur"),
        imageUrl: vxRule(true, null, "blur"),
        message: vxRule(true, null, "blur"),
        evaluate: vxRule(true, null, "blur"),
        reason: vxRule(true, null, "blur"),
        consumables: vxRule(true, null, "blur"),
        applicantName: vxRule(true, null, "blur"),
        mobile: vxRule(true, null, "blur"),
        radioUrl: vxRule(true, null, "blur"),
        openid: vxRule(true, null, "blur"),
        finishTime: vxRule(true, null, "blur"),
        type: vxRule(true, null, "blur"),
        detail: vxRule(true, null, "blur"),
        repairCancelReason: vxRule(true, null, "blur"),
        repairCancelReasonRemark: vxRule(true, null, "blur"),
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
    getRepairInfoById(id) {
      httpService("/api/repairInfo/queryById", { id: id }, "get").then(
        (data) => {
          this.addRepairInfoForm = data;
        }
      );
    },
    clear() {
      this.dialogFormVisible = false;
      //清空form
      this.addRepairInfoForm.id = "";
      this.addRepairInfoForm.type = "";
      this.addRepairInfoForm.status = "";
      this.addRepairInfoForm.repairDate = "";
      this.addRepairInfoForm.address = "";
      this.addRepairInfoForm.description = "";
      this.addRepairInfoForm.imageUrl = "";
      this.addRepairInfoForm.message = "";
      this.addRepairInfoForm.evaluate = "";
      this.addRepairInfoForm.reason = "";
      this.addRepairInfoForm.consumables = "";
      this.addRepairInfoForm.applicantName = "";
      this.addRepairInfoForm.remarks = "";
      this.addRepairInfoForm.mobile = "";
      this.addRepairInfoForm.star = "";
      this.addRepairInfoForm.radioUrl = "";
      this.addRepairInfoForm.evaluateDate = "";
      this.addRepairInfoForm.openid = "";
      this.addRepairInfoForm.campus = "";
      this.addRepairInfoForm.finishTime = "";
      this.addRepairInfoForm.allocatedTime = "";
      this.addRepairInfoForm.detail = "";
      this.addRepairInfoForm.repairCancelReason = "";
      this.addRepairInfoForm.repairCancelReasonRemark = "";
      this.addRepairInfoForm.repairCancelDate = "";
      this.addRepairInfoForm.location = "";
      this.addRepairInfoForm.level = "";
    },
    save() {
      this.$refs.addRepairInfoRef.validate((valid) => {
        if (valid) {
          let params = new FormData(); //创建一个form对象,必须是form对象否则后端接受不到数据
          params.append("type", this.addRepairInfoForm.type);
          params.append("address", this.addRepairInfoForm.address);
          params.append("description", this.addRepairInfoForm.description);
          params.append("imageUrl", this.addRepairInfoForm.imageUrl);
          params.append("message", this.addRepairInfoForm.message);
          params.append("reason", this.addRepairInfoForm.reason);
          params.append("consumables", this.addRepairInfoForm.consumables);
          params.append("applicantName", this.addRepairInfoForm.applicantName);
          params.append("remarks", this.addRepairInfoForm.remarks);
          params.append("mobile", this.addRepairInfoForm.mobile);
          params.append("radioUrl", this.addRepairInfoForm.radioUrl);
          params.append("detail", this.addRepairInfoForm.detail);
          params.append("status", this.addRepairInfoForm.status);

          if (this.addRepairInfoForm.id) {
            params.append("id", this.addRepairInfoForm.id);
            httpService("/api/repairInfo/edit", params, "post").then((data) => {
              if (data.code == 200) {
                ElMessage.success("修改成功");
                this.clear();
                //刷新父组件的数据
                this.$parent.getRepairInfoList();
              } else {
                ElMessage.error("修改失败");
              }
            });
          } else {
            httpService("/api/repairInfo/add", params, "post").then((data) => {
              if (data.code == 200) {
                ElMessage.success("添加成功");
                this.clear();
                //刷新父组件的数据
                this.$parent.getRepairInfoList();
              } else {
                ElMessage.error("添加失败");
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