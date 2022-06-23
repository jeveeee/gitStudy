<template>
  <addEditRepairInfoView ref="addEditRepairInfoView" />
  <addFileView ref="addFileView" />
  <el-main>
    <div class="teacher">
      <div class="app-table">
        <div class="app-body">
          <div class="app-search">
            <el-form :inline="true" class="demo-form-inline" v-model="form">
              <el-form-item label="申请人姓名">
                <el-input
                  placeholder="请输入申请人姓名"
                  v-model="form.applicantName"
                ></el-input>
              </el-form-item>
              <el-form-item label="报修地点">
                <el-input
                  placeholder="请输入报修地点"
                  v-model="form.address"
                ></el-input>
              </el-form-item>
              <el-form-item label="故障描述">
                <el-input
                  placeholder="请输入故障描述"
                  v-model="form.description"
                ></el-input>
              </el-form-item>
              <el-form-item
                label="报修状态"
                :label-width="formLabelWidth"
                prop="status"
                v-model="form.status"
              >
                <el-select
                  v-model="form.status"
                  placeholder="请选择报修状态"
                  clearable
                >
                  <el-option label="新报修" :value="1"></el-option>
                  <el-option label="维修中" :value="2"></el-option>
                  <el-option label="已完成" :value="3"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item>
                <el-button
                  plain
                  type="primary"
                  @click="onSubmit"
                  @keyup.enter="searchEnter"
                  >搜索</el-button
                >
                <el-button plain type="success" @click="add">添加</el-button>
                <el-button plain type="warning" @click="exportExcel"
                  >导出报表</el-button
                >
                <el-button plain type="danger" @click="importExcel"
                  >批量导入<el-icon class="el-icon--right"><Upload /></el-icon
                ></el-button>
              </el-form-item>
            </el-form>
          </div>
          <el-table
            size="small"
            :data="list"
            style="width: 100%"
            border
            :row-class-name="tableRowClassName"
          >
            <el-table-column prop="id" label="主键" width="90px" />
            <el-table-column
              prop="applicantName"
              label="申请人姓名"
              width="120px"
            />
                 <el-table-column prop="type" label="报修状态" width="100px">
              <template #default="scpd">
                <span v-if="scpd.row.status == '1'">新报修</span>
                <span v-if="scpd.row.status == '2'">维修中</span>
                <span v-if="scpd.row.status == '3'">已完成</span>
              </template>
            </el-table-column>
            <el-table-column prop="type" label="报修类型" width="100px">
              <template #default="scpd">
                <span v-if="scpd.row.type == '1'">电教报修</span>
                <span v-if="scpd.row.type == '2'">总务报修</span>
              </template>
            </el-table-column>
                   <el-table-column prop="address" label="报修地点" />

            <el-table-column prop="description" label="故障描述" />

            <el-table-column label="操作" width="140px">
              <template style="display: block" #default="scope">
                <el-button
                  plain
                  size="small"
                  type="success"
                  @click="editRepairInfo(scope.row)"
                  >编辑
                </el-button>
                <el-button
                  plain
                  size="small"
                  type="danger"
                  @click="deleteRepairInfo(scope.row.id)"
                  >删除
                </el-button>
              </template>
            </el-table-column>
            <template v-slot:empty>
              <el-empty description="暂无数据"></el-empty>
            </template>
          </el-table>
          <el-config-provider :locale="locale">
            <el-pagination
              :total="total"
              :page-size="limit"
              :page-sizes="[5, 10, 20, 30, 40, 50, 100]"
              layout="total, sizes, prev, pager, next, jumper"
              @size-change="changePageSize"
              @current-change="changeCurrentPage"
            >
            </el-pagination>
          </el-config-provider>
        </div>
      </div>
    </div>
  </el-main>
</template>
<script>
import addEditRepairInfoView from "@/components/addEditRepairInfo.vue";
import addFileView from "@/components/addExcelFile.vue";
import { ElConfigProvider, ElMessageBox, ElMessage } from "element-plus";
import zhCn from "element-plus/lib/locale/lang/zh-cn";
import { httpService } from "@/utils/httpService";
import { Picture as IconPicture, Upload } from "@element-plus/icons-vue";

export default {
  name: "RepairInfoListView",
  data() {
    return {
      type: 0,
      title: "",
      page: 0,
      pageSize: 10,
      total: 0,
      limit: 10,
      list: [],
      form: {
        address: "",
        description: "",
        imageUrl: "",
        message: "",
        evaluate: "",
        status: "",
        reason: "",
        consumables: "",
        applicantName: "",
        remarks: "",
        mobile: "",
        radioUrl: "",
        openid: "",
        finishTime: "",
        allocatedTime: "",
        detail: "",
        repairCancelReason: "",
        repairCancelReasonRemark: "",
      },
    };
  },
  components: {
    addEditRepairInfoView,
    addFileView,
    IconPicture,
    ElConfigProvider,
    ElMessageBox,
    ElMessage,
    Upload,
  },
  setup() {
    return {
      locale: zhCn,
    };
  },
  mounted() {
    this.getRepairInfoList();
  },
  watch: {
    $route() {
      this.getRepairInfoList();
    },
  },
  methods: {
    searchEnter: function (e) {
      // 使用 which 和 keyCode 属性来解决兼容问题
      var keyCode = window.event ? e.keyCode : e.which;
      var val = e.target.value;
      if (keyCode == 13 && val) {
        this.getRepairInfoList();
      }
    },
    add() {
      this.$refs.addEditRepairInfoView.title = "添加报修信息";
      this.$refs.addEditRepairInfoView.personnelType = this.type;
      this.$refs.addEditRepairInfoView.dialogFormVisible = true;
    },
    editRepairInfo(obj) {
      this.$refs.addEditRepairInfoView.title = "修改信息";
      this.$refs.addEditRepairInfoView.getRepairInfoById(obj.id);
      this.$refs.addEditRepairInfoView.dialogFormVisible = true;
    },
    deleteRepairInfo(id) {
      ElMessageBox.confirm("确定要删除本条记录吗？", "提醒", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          httpService("/api/repairInfo/deleteById", "id=" + id, "post").then(
            (data) => {
              if (data.code == 200) {
                ElMessage.success("删除成功");
                this.getRepairInfoList();
              } else {
                ElMessage.error("删除失败，请重试！");
              }
            }
          );
        })
        .catch(() => {});
    },
    reloadData() {
      this.page = 1;
      this.getRepairInfoList();
    },
    importExcel() {
      this.$refs.addFileView.dialogFormVisible = true;
      this.$refs.addFileView.title = "批量导入报修信息";
      this.$refs.addFileView.uploadApiUrl = "/api/repairInfo/importExcel";
    },
    exportExcel() {
      httpService(
        "/api/repairInfo/exportExcel",
        {
          address: this.form.address,
          description: this.form.description,
          imageUrl: this.form.imageUrl,
          message: this.form.message,
          evaluate: this.form.evaluate,
          reason: this.form.reason,
          consumables: this.form.consumables,
          applicantName: this.form.applicantName,
          remarks: this.form.remarks,
          mobile: this.form.mobile,
          radioUrl: this.form.radioUrl,
          openid: this.form.openid,
          status: this.form.status,
          finishTime: this.form.finishTime,
          allocatedTime: this.form.allocatedTime,
          detail: this.form.detail,
          repairCancelReason: this.form.repairCancelReason,
          repairCancelReasonRemark: this.form.repairCancelReasonRemark,
        },
        "get"
      ).then((data) => {
        if (data.code == 200) {
          window.open(data.msg, "_blank");
        }
      });
    },
    onSubmit() {
      this.getRepairInfoList();
    },
    // 每页记录数改变，size：回调参数，表示当前选中的“每页条数”
    changePageSize(size) {
      this.pageSize = size;
      this.limit = size;
      this.getRepairInfoList();
    },

    // 改变页码，page：回调参数，表示当前选中的“页码”
    changeCurrentPage(page) {
      this.page = page;
      this.getRepairInfoList();
    },
    tableRowClassName({ row, rowIndex }) {
      return "";
    },
    getRepairInfoList() {
      httpService(
        "/api/repairInfo/list/" + this.page + "/" + this.pageSize,
        {
          address: this.form.address,
          description: this.form.description,
          imageUrl: this.form.imageUrl,
          message: this.form.message,
          evaluate: this.form.evaluate,
          reason: this.form.reason,
          status: this.form.status,
          consumables: this.form.consumables,
          applicantName: this.form.applicantName,
          remarks: this.form.remarks,
          mobile: this.form.mobile,
          radioUrl: this.form.radioUrl,
          openid: this.form.openid,
          finishTime: this.form.finishTime,
          allocatedTime: this.form.allocatedTime,
          detail: this.form.detail,
          repairCancelReason: this.form.repairCancelReason,
          repairCancelReasonRemark: this.form.repairCancelReasonRemark,
        },
        "get"
      ).then((data) => {
        this.list = data.list;
        this.total = data.total;
        this.pageCount = data.pages;
      });
    },
  },
};
</script>
<style scoped>
.el-main {
  padding: 0px;
}

.teacher-content {
  width: 100%;
}

.app-table {
  background: #eff3f5;
  padding: 10px;
}

.app-search {
  padding-top: 10px;
  /* border-top: 1px solid #dedede; */
  /* margin-top: 20px; */
}

.app-separator {
  padding-top: 10px;
  padding-bottom: 20px;
  padding-left: 10px;
}

.app-body {
  background: white;
  padding: 20px 20px 10px 20px;
  margin-bottom: 10px;
}

.el-table .warning-row {
  --el-table-tr-bg-color: var(--el-color-warning-lighter);
}

.el-table .success-row {
  --el-table-tr-bg-color: var(--el-color-success-lighter);
}

.el-pagination {
  text-align: center;
  margin-top: 10px;
}
</style>

