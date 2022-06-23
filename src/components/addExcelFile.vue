<template>
  <el-dialog v-model="dialogFormVisible" :title="title" @close="closeDialog">
    <el-upload
      ref="upload"
      class="upload-demo"
      :limit="1"
      :on-exceed="handleExceed"
      :auto-upload="false"
      :file-list="fileList"
      :on-change="fileChange"
      :before-remove="handleRemove"
      accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel"
    >
      <template #trigger>
        <el-button type="primary">选择文件</el-button>
      </template>
      <el-button class="upload-btn" type="success" @click="submitUpload"
        >上传数据</el-button
      >
      <template #tip>
        <div class="el-upload__tip" style="color: red">
          选择正确模版的Excel文件，并且只能选择一个文件!
        </div>
      </template>
    </el-upload>
  </el-dialog>
</template>

<script>
import { ref } from "vue";
import { httpService } from "@/utils/httpService";
import { ElMessage, ElForm } from "element-plus";

export default {
  name: "AddExcelFile",
  components: { ElForm, ElMessage },
  data() {
    return {
      dialogFormVisible: ref(false),
      formLabelWidth: "120px",
      title: "",
      uploadApiUrl: "",
      fileList: [],
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
    clear() {
      this.dialogFormVisible = false;
      this.$refs.upload.clearFiles();
      this.fileList = [];
    },
    fileChange(file, fileList) {
      this.fileList = fileList;
    },
    handleRemove() {
      this.$refs.upload.clearFiles();
      this.fileList = [];
    },
    handleExceed(files){
      this.$refs.upload.clearFiles()
      this.fileList = [];
      this.$refs.upload.handleStart(files[0])
    },
    submitUpload() {
      let params = new FormData(); //创建一个form对象,必须是form对象否则后端接受不到数据
      if (this.fileList.length > 0) {
        if (this.fileList[0].raw) {
          params.append("file", this.fileList[0].raw);
          let config = { "Content-Type": "multipart/form-data" };
          httpService(this.uploadApiUrl, params, "post", config).then(
            (data) => {
              if (data.code == 200) {
                ElMessage.success(data.msg);
                this.clear();
                //刷新父组件的数据
                this.$parent.reloadData();
              } else {
                ElMessage.error(data.msg);
              }
            }
          );
        }
      }else{
          ElMessage.error("请选择文件");
      }
    },
  },
};
</script>
<style scoped>
.upload-demo {
  padding: 20px;
  border: 1px solid #dedede;
  margin-bottom: 60px;
}
.upload-btn {
  margin-left: 10px;
}
.el-upload__tip {
  padding-top: 10px;
  padding-bottom: 10px;
}
</style>