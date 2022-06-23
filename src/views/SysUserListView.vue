<template>
  <addEditSysUserView ref="addEditSysUserView" />
  <addFileView ref="addFileView" />
  <el-main>
    <div class="teacher">
      <div class="app-table">
        <div class="app-body">
          <div class="app-search">
            <el-form :inline="true" class="demo-form-inline" v-model="form">
              <el-form-item label="用户名">
                <el-input
                  placeholder="请输入用户名"
                  v-model="form.userName"
                ></el-input>
              </el-form-item>

              <el-form-item
                label="用户类型"
                :label-width="formLabelWidth"
                prop="userType"
              >
                <el-select
                  v-model="form.userType"
                  placeholder="请选择类型"
                  clearable
                >
                  <el-option label="管理员" :value="1"></el-option>
                  <el-option label="报修员" :value="2"></el-option>
                  <el-option label="维修员" :value="3"></el-option>
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
            <el-table-column prop="id" label="序号" width="90" />
            <el-table-column prop="userName" label="用户名" />
            <el-table-column prop="mobile" label="联系电话" />
            <el-table-column prop="userType" label="用户类型">
              <template #default="scp">
                <span v-if="scp.row.userType == '1'">管理员</span>
                <span v-if="scp.row.userType == '2'">报修员</span>
                <span v-if="scp.row.userType == '3'">维修员</span>
              </template>
            </el-table-column>
            <el-table-column prop="isDelete" label="是否可用">
              <template #default="scpd">
                <span v-if="scpd.row.isDelete == '0'">可用</span>
                <span v-if="scpd.row.isDelete == '1'">禁用</span>
              </template>
            </el-table-column>
            <el-table-column label="操作" width="140px">
              <template style="display: block" #default="scope">
                <el-button
                  plain
                  size="small"
                  type="success"
                  @click="editSysUser(scope.row)"
                  >编辑
                </el-button>
                <el-button
                  plain
                  size="small"
                  type="danger"
                  @click="deleteSysUser(scope.row.id)"
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
import addEditSysUserView from "@/components/addEditSysUser.vue";
import addFileView from "@/components/addExcelFile.vue";
import { ElConfigProvider, ElMessageBox, ElMessage } from "element-plus";
import zhCn from "element-plus/lib/locale/lang/zh-cn";
import { httpService } from "@/utils/httpService";
import { Picture as IconPicture, Upload } from "@element-plus/icons-vue";

export default {
  name: "SysUserListView",
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
        userName: "",
        userPassword: "",
        userType: "",
      },
    };
  },
  components: {
    addEditSysUserView,
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
    this.getSysUserList();
  },
  watch: {
    $route() {
      this.getSysUserList();
    },
  },
  methods: {
    searchEnter: function (e) {
      // 使用 which 和 keyCode 属性来解决兼容问题
      var keyCode = window.event ? e.keyCode : e.which;
      var val = e.target.value;
      if (keyCode == 13 && val) {
        this.getSysUserList();
      }
    },
    add() {
      this.$refs.addEditSysUserView.title = "添加管理员信息";
      this.$refs.addEditSysUserView.personnelType = this.type;
      this.$refs.addEditSysUserView.dialogFormVisible = true;
    },
    editSysUser(obj) {
      this.$refs.addEditSysUserView.title = "修改信息";
      this.$refs.addEditSysUserView.getSysUserById(obj.id);
      this.$refs.addEditSysUserView.dialogFormVisible = true;
    },
    deleteSysUser(id) {
      ElMessageBox.confirm("确定要删除本条记录吗？", "提醒", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          httpService("/api/sysUser/deleteById", "id=" + id, "post").then(
            (data) => {
              if (data.code == 200) {
                ElMessage.success("删除成功");
                this.getSysUserList();
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
      this.getSysUserList();
    },
    importExcel() {
      this.$refs.addFileView.dialogFormVisible = true;
      this.$refs.addFileView.title = "批量导入管理员信息";
      this.$refs.addFileView.uploadApiUrl = "/api/sysUser/importExcel";
    },
    exportExcel() {
      httpService(
        "/api/sysUser/exportExcel",
        {
          userName: this.form.userName,
          userPassword: this.form.userPassword,
        },
        "get"
      ).then((data) => {
        if (data.code == 200) {
          window.open(data.msg, "_blank");
        }
      });
    },
    onSubmit() {
      this.getSysUserList();
    },
    // 每页记录数改变，size：回调参数，表示当前选中的“每页条数”
    changePageSize(size) {
      this.pageSize = size;
      this.limit = size;
      this.getSysUserList();
    },

    // 改变页码，page：回调参数，表示当前选中的“页码”
    changeCurrentPage(page) {
      this.page = page;
      this.getSysUserList();
    },
    tableRowClassName({ row, rowIndex }) {
      return "";
    },
    getSysUserList() {
      httpService(
        "/api/sysUser/list/" + this.page + "/" + this.pageSize,
        {
          userName: this.form.userName,
          userPassword: this.form.userPassword,
          userType: this.form.userType,
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

